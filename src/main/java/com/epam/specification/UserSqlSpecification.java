package com.epam.specification;

import com.epam.model.Employee;

import java.util.Optional;

public class UserSqlSpecification implements SqlSpecification<Employee> {
    private String login;
    private String password;

    public UserSqlSpecification(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public Optional<Employee> specify() {
        // find employee in BD
        Employee employee = new Employee("John", "Smith", "J12345", "qwerty", false);
        return Optional.of(employee);
    }
}
