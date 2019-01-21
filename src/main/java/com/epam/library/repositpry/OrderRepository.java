package com.epam.library.repositpry;

import com.epam.library.builder.Builder;
import com.epam.library.builder.OrderBuilder;
import com.epam.library.exception.RepositoryException;
import com.epam.library.model.Book;
import com.epam.library.model.Order;
import com.epam.library.model.Reader;
import com.epam.library.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderRepository extends AbstractRepository<Order> {
    private static final String SELECT_QUERY = "select * from `order`\n";
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
    public List<Order> query(SqlSpecification specification) throws RepositoryException {
        try {

            String query = SELECT_QUERY + specification.toSql();
            List<String> parameters = specification.getParameters();

            return executeQuery(query, parameters);
        } catch (SQLException e) {
            throw new RepositoryException("Cannot execute query" + e);
        }
    }

    //map<Str, obj> fields
    // fields.put(NAME_OF_COLUMN, employee.getId);
    @Override
    public Optional<Order> queryForSingleResult(SqlSpecification specification) throws RepositoryException {
        try {
            String query = SELECT_QUERY + specification.toSql();
            List<String> parameters = specification.getParameters();

            return executeQueryForSingleResult(query, parameters);
        } catch (SQLException e) {
            throw new RepositoryException("Cannot execute query for single result" + e);
        }
    }

    protected Builder<Order> getBuilder() {
        return new OrderBuilder();
    }


    @Override
    public void save(Order order) throws RepositoryException {

        Map<Integer, Object> map = new HashMap<>();
        int i = 1;

        map.put(i++, order.isInReadingRoom());
        map.put(i++, order.getTakingDate());
        map.put(i++, order.getReturnDate());
        Book book = order.getBook();
        map.put(i++, book.getId());
        Reader reader = order.getReader();
        map.put(i++, reader.getId());

        try {
            if (order.getId() == null) {
                executeSave(map, INSERT_QUERY);
            } else {
                map.put(i, order.getId());
                executeSave(map, UPDATE_QUERY);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Cannot execute save entity" + e);
        }
    }

    @Override
    public void remove(Order order) {
        throw new UnsupportedOperationException("Operation 'remove' is not defined");
    }

}
