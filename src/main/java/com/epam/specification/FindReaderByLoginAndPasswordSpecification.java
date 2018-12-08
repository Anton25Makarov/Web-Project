package com.epam.specification;

import com.epam.builder.Builder;
import com.epam.builder.ReaderBuilder;
import com.epam.model.Employee;
import com.epam.model.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class FindReaderByLoginAndPasswordSpecification implements SqlSpecification<Reader> {
    private static final String QUERY_SELECT = "select * from reader where login = ? and password = ?;";

    private String login;
    private String password;

    public FindReaderByLoginAndPasswordSpecification(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public Optional<Reader> specify() {
        // find employee in BD
        Reader reader = new Reader(1L, "Johnatan", "Smith JR",
                "J54321", "ytrewq", "+3752945992585");
        return Optional.of(reader);
    }

    @Override
    public Optional<Reader> toSql(Builder<Reader> builder, Connection connection, String... strings)
            throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            Reader reader = null;
            if (resultSet.next()) {
                reader = builder.build(resultSet);
            }

            return Optional.ofNullable(reader);
        } catch (SQLException e) {
            throw new SQLException(); //own exception
        }
    }
}
