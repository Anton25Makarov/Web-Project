package com.epam.library.repositpry;

import com.epam.library.builder.Builder;
import com.epam.library.builder.OrderBuilder;
import com.epam.library.model.Book;
import com.epam.library.model.Order;
import com.epam.library.model.Reader;
import com.epam.library.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderRepository extends AbstractRepository<Order> {
    private static final String SELECT_QUERY = "select * from `order` ";
    private static final String INSERT_QUERY =
            "insert into `order` (is_in_reading_room, taking_date, return_date, book_id, reader_id)\n" +
                    "values (?, ?, ?, ?, ?);";
    private static final String UPDATE_QUERY =
            "update `order`\n" +
                    "set is_in_reading_room = ?,\n" +
                    "    taking_date        = ?,\n" +
                    "    return_date        = ?,\n" +
                    "    book_id            = ?,\n" +
                    "    reader_id          = ?\n" +
                    "where id = ?;";

    public OrderRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<Order> query(SqlSpecification specification) throws SQLException {

        String query = SELECT_QUERY + specification.toSql();
        List<String> parameters = specification.getParameters();
        Builder<Order> builder = getBuilder();


        return executeQuery(builder, query, parameters);
    }

    //map<Str, obj> fields
    // fields.put(NAME_OF_COLUMN, employee.getId);
    @Override
    public Optional<Order> queryForSingleResult(SqlSpecification specification) throws SQLException {
        String query = SELECT_QUERY + specification.toSql();
        List<String> parameters = specification.getParameters();

        Builder<Order> builder = getBuilder();

        return executeQueryForSingleResult(builder, query, parameters);
    }

    protected Builder<Order> getBuilder() {
        return new OrderBuilder();
    }


    @Override
    public boolean save(Order order) throws SQLException {

        Long orderId = order.getId();
        boolean inReadingBook = order.isInReadingRoom();
        java.util.Date takingDate = order.getTakingDate();
        java.util.Date returnDate = order.getReturnDate();
        Date takingDateSql = null;
        Date returnDateSql = null;
        if (takingDate != null) {
            takingDateSql = new Date(order.getTakingDate().getTime());
        }
        if (returnDate != null) {
            returnDateSql = new Date(order.getReturnDate().getTime());
        }
        Book book = order.getBook();
        Reader reader = order.getReader();
        Long bookId = book.getId();
        Long readerId = reader.getId();

        int i = FIRST_COLUMN;

        if (orderId != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
                preparedStatement.setBoolean(i++, inReadingBook);
                preparedStatement.setDate(i++, takingDateSql);
                preparedStatement.setDate(i++, returnDateSql);
                preparedStatement.setLong(i++, bookId);
                preparedStatement.setLong(i++, readerId);
                preparedStatement.setLong(i, orderId);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException(); //own exception
            }
        } else {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
                preparedStatement.setBoolean(i++, inReadingBook);
                preparedStatement.setDate(i++, takingDateSql);
                preparedStatement.setDate(i++, returnDateSql);
                preparedStatement.setLong(i++, bookId);
                preparedStatement.setLong(i, readerId);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException(); //own exception
            }
        }

        return true;
    }

    @Override
    public boolean remove(Order order) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
