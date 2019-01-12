package com.epam.library.repositpry;

import com.epam.library.builder.Builder;
import com.epam.library.builder.OrderBuilder;
import com.epam.library.model.Order;
import com.epam.library.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderRepository extends AbstractRepository<Order> {
    private static final String SELECT_QUERY = "select * from `order` ";
    private static final String INSERT_QUERY =
            "insert into `order` (is_in_reading_room, taking_date, return_date, book_id, reader_id)\n" +
                    "values (?, ?, ?, ?, ?);";


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
    public Optional<Order> queryForSingleResult(SqlSpecification specification) {
        throw new UnsupportedOperationException();
    }

    protected Builder<Order> getBuilder() {
        return new OrderBuilder();
    }

    protected Optional<Order> executeQueryForSingleResult(
            Builder<Order> builder, String query, List<String> parameters) {
        throw new UnsupportedOperationException(); // add text error as parameter
    }

    @Override
    public boolean save(Order order) throws SQLException {

        return true;
    }

    @Override
    public boolean remove(Order order) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
