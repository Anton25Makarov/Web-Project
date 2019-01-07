package com.epam.library.repositpry;

import com.epam.library.builder.Builder;
import com.epam.library.builder.ReaderBuilder;
import com.epam.library.model.Reader;
import com.epam.library.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReaderRepository extends AbstractRepository<Reader> {
    public static final int FIRST_COLUMN = 1;
    private static final String SELECT_QUERY = "select * from reader ";
    private static final String REMOVE_QUERY = "delete from reader where id = ?";
    private static final String INSERT_QUERY =
            "insert into reader (login, password, name, surname, telephone)\n" +
                    "values (?, ?, ?, ?, ?);";
    private static final String UPDATE_QUERY =
            "update reader\n" +
                    "set login     = ?,\n" +
                    "    password  = ?,\n" +
                    "    name      = ?,\n" +
                    "    surname   = ?,\n" +
                    "    telephone = ?\n" +
                    "where id = ?;";


    public ReaderRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<Reader> query(SqlSpecification specification) throws SQLException {
        String query = SELECT_QUERY + specification.toSql();
        List<String> parameters = specification.getParameters();
        Builder<Reader> builder = getBuilder();

        return executeQuery(builder, query, parameters);
    }

    @Override
    public Optional<Reader> queryForSingleResult(SqlSpecification specification)
            throws SQLException {

        String query = SELECT_QUERY + specification.toSql();
        List<String> parameters = specification.getParameters();

        Builder<Reader> builder = getBuilder();

        return executeQueryForSingleResult(builder, query, parameters);
    }

    @Override
    public boolean save(Reader reader) throws SQLException {
        String name = reader.getName();
        String surname = reader.getSurname();
        String login = reader.getLogin();
        String password = reader.getPassword();
        String telephoneNumber = reader.getTelephoneNumber();

        int i = FIRST_COLUMN;
        if (reader.getId() == null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
                preparedStatement.setString(i++, login);
                preparedStatement.setString(i++, password);
                preparedStatement.setString(i++, name);
                preparedStatement.setString(i++, surname);
                preparedStatement.setString(i, telephoneNumber);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException(); //own exception
            }
        } else {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

                preparedStatement.setString(i++, login);
                preparedStatement.setString(i++, password);
                preparedStatement.setString(i++, name);
                preparedStatement.setString(i++, surname);
                preparedStatement.setString(i++, telephoneNumber);
                preparedStatement.setLong(i, reader.getId());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException(); //own exception
            }
        }

        return true;
    }

    @Override
    public boolean remove(Reader reader) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_QUERY)) {
            preparedStatement.setLong(FIRST_COLUMN, reader.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(); //own exception
        }

        return true;
    }

    @Override
    protected Builder<Reader> getBuilder() {
        return new ReaderBuilder();
    }
}
