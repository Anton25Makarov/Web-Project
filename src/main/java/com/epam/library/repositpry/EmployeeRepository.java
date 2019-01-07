package com.epam.library.repositpry;

import com.epam.library.builder.Builder;
import com.epam.library.builder.EmployeeBuilder;
import com.epam.library.model.Employee;
import com.epam.library.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository extends AbstractRepository<Employee> {
    private static final String SELECT_QUERY = "select * from employee ";
    private static final String REMOVE_QUERY = "delete from employee where id = ?";
    private static final String INSERT_QUERY =
            "insert into employee (login, password, name, surname, is_admin)\n" +
                    "values (?, ?, ?, ?, ?);";
    private static final String UPDATE_QUERY =
            "update employee\n" +
                    "set login    = ?,\n" +
                    "    password = ?,\n" +
                    "    name     = ?,\n" +
                    "    surname  = ?,\n" +
                    "    is_admin = ?\n" +
                    "where id = ?;";

    public EmployeeRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<Employee> query(SqlSpecification specification) throws SQLException {
        String query = SELECT_QUERY + specification.toSql();
        List<String> parameters = specification.getParameters();
        Builder<Employee> builder = getBuilder();

        return executeQuery(builder, query, parameters);
    }

    @Override
    public Optional<Employee> queryForSingleResult(SqlSpecification specification) throws SQLException {

        String query = SELECT_QUERY + specification.toSql();
        List<String> parameters = specification.getParameters();

        Builder<Employee> builder = getBuilder();

        return executeQueryForSingleResult(builder, query, parameters);
    }

    @Override
    public boolean save(Employee employee) throws SQLException {

        String name = employee.getName();
        String surname = employee.getSurname();
        String login = employee.getLogin();
        String password = employee.getPassword();
        boolean isAdmin = employee.isAdmin();

        if (employee.getId() == null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, name);
                preparedStatement.setString(4, surname);
                preparedStatement.setBoolean(5, isAdmin);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException(); //own exception
            }
        } else {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, name);
                preparedStatement.setString(4, surname);
                preparedStatement.setBoolean(5, isAdmin);
                preparedStatement.setLong(6, employee.getId());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException(); //own exception
            }
        }
        return true;
    }

    @Override
    public boolean remove(Employee employee) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_QUERY)) {
            preparedStatement.setLong(FIRST_COLUMN, employee.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(); //own exception
        }

        return true;
    }

    protected Builder<Employee> getBuilder() {
        return new EmployeeBuilder();
    }
}
//map<Str, obj> fields
// fields.put(NAME_OF_COLUMN, employee.getId);
