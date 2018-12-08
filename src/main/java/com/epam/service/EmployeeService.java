package com.epam.service;

import com.epam.model.Employee;
import com.epam.repositpry.AbstractRepository;
import com.epam.repositpry.RepositoryFactory;
import com.epam.specification.SqlSpecification;
import com.epam.specification.FindEmployeeByLoginAndPasswordSpecification;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class EmployeeService {

    public Optional<Employee> login(String login, String password) throws SQLException, IOException {
//        Repository<Employee> employeeRepository = new EmployeeRepository(); // = RepositoryFactory.(...)
        AbstractRepository employeeRepository = RepositoryFactory.createEmployeeRepository();

        SqlSpecification specification = new FindEmployeeByLoginAndPasswordSpecification(login, password);

        return employeeRepository.queryForSingleResult(specification);
    }
}