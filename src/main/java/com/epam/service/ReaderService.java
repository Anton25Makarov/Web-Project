package com.epam.service;

import com.epam.model.Reader;
import com.epam.repositpry.ReaderRepository;
import com.epam.repositpry.Repository;
import com.epam.specification.FindReaderByLoginAndPasswordSpecification;
import com.epam.specification.SqlSpecification;

import java.sql.SQLException;
import java.util.Optional;

public class ReaderService {

    public Optional<Reader> login(String login, String password) throws SQLException {
        Repository<Reader> employeeRepository = new ReaderRepository();

        SqlSpecification specification = new FindReaderByLoginAndPasswordSpecification(login, password);

        return employeeRepository.queryForSingleResult(specification);
    }
}