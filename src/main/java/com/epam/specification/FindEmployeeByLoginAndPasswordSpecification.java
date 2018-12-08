package com.epam.specification;

import com.epam.model.Employee;

import java.util.Optional;

public class FindEmployeeByLoginAndPasswordSpecification implements SqlSpecification<Employee> {
    private String login;
    private String password;

    public FindEmployeeByLoginAndPasswordSpecification(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public Optional<Employee> specify() {
        // find employee in BD


        Employee employee = new Employee(1L, "John", "Smith",
                "J12345", "qwerty", true);
        return Optional.of(employee);
    }

    @Override
    public String toSql() {

        String s = "select * from employee ";// + ...

        return null;
    }
}
