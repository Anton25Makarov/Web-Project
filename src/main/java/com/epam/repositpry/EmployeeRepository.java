package com.epam.repositpry;

import com.epam.builder.Builder;
import com.epam.builder.EmployeeBuilder;
import com.epam.model.Employee;
import com.epam.specification.SqlSpecification;

import java.util.List;
import java.util.Optional;

public class EmployeeRepository extends AbstractRepository {

    @Override
    public List<Employee> query(SqlSpecification userSqlSpecification) {
        return null;
    }

    @Override
    public Optional<Employee> queryForSingleResult(SqlSpecification userSqlSpecification) {
        return userSqlSpecification.specify();
    }

    public Builder<Employee> getBuilder() {
        return new EmployeeBuilder();
    }
}
