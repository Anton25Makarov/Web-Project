package com.epam.repositpry;

import com.epam.model.Employee;
import com.epam.specification.SqlSpecification;

import java.util.List;
import java.util.Optional;

public class UserRepository implements Repository<Employee> {

    @Override
    public List<Employee> query(SqlSpecification userSqlSpecification) {
        return null;
    }

    @Override
    public Optional<Employee> queryForSingleResult(SqlSpecification userSqlSpecification) {
        return userSqlSpecification.specify();
    }
}
