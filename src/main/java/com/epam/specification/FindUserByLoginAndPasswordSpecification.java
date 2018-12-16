package com.epam.specification;

import com.epam.model.Employee;
import com.epam.model.Entity;

import java.util.ArrayList;
import java.util.List;

public class FindUserByLoginAndPasswordSpecification implements SqlSpecification {
    //            private static final String QUERY_CONDITION = "select * from employee where login = ? and password = ?;";
//    private static final String QUERY_CONDITION = "where login = ? and password = ?;";
    private static final String QUERY_CONDITION = "where login = ? and password = ?;";
    //    private static  final String
    private String login;
    private String password;

    public FindUserByLoginAndPasswordSpecification(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toSql() {
        return QUERY_CONDITION;
    }

    @Override
    public List<String> getParameters() {
        List<String> parameters = new ArrayList<>();
        parameters.add(login);
        parameters.add(password);
        return parameters;
    }

  /*  @Override
    public Optional<Employee> specify() {
        // find employee in BD


        Employee employee = new Employee(1L, "John", "Smith",
                "J12345", "qwerty", true);
        return Optional.of(employee);
    }*/

   /* @Override
    public String toSql() {
        return QUERY_CONDITION;
    }*/

//    @Override
//    public String toSql() {
//        return QUERY_CONDITION + String.format(QUERY_CONDITION, login, password);
//        return QUERY_CONDITION + "where login = " + login + " and password = " + password + ";";
//    }


  /*  @Override
    public Optional<Employee> toSql(Builder<Employee> employeeBuilder, Connection connection, String... strings)
            throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CONDITION)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            Employee employee = null;
            if (resultSet.next()) {
                employee = employeeBuilder.build(resultSet);

            }

            return Optional.ofNullable(employee);
        } catch (SQLException e) {
            throw new SQLException(); //own exception
        }
    }*/

}
// когда возращать connection