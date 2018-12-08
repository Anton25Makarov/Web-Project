package com.epam.logic;

import com.epam.model.Employee;
import com.epam.repositpry.Repository;
import com.epam.repositpry.EmployeeRepository;
import com.epam.specification.SqlSpecification;
import com.epam.specification.FindEmployeeByLoginAndPasswordSpecification;

import java.util.Optional;

public class EmployeeService {

    public Optional<Employee> login(String login, String password) {
        Repository<Employee> employeeRepository = new EmployeeRepository(); // = RepositoryFactory.(...)

        SqlSpecification specification = new FindEmployeeByLoginAndPasswordSpecification(login, password);

        return employeeRepository.queryForSingleResult(specification);
    }
}