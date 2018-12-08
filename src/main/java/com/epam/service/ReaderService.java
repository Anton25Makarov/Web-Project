package com.epam.service;

import com.epam.connection.ConnectionPool;
import com.epam.model.Reader;
import com.epam.repositpry.AbstractRepository;
import com.epam.repositpry.RepositoryFactory;
import com.epam.specification.FindReaderByLoginAndPasswordSpecification;
import com.epam.specification.SqlSpecification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class ReaderService {

    public Optional<Reader> login(String login, String password) throws SQLException, IOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.takeConnection();

        AbstractRepository readerRepository = RepositoryFactory.createReaderRepository(connection);

        SqlSpecification specification = new FindReaderByLoginAndPasswordSpecification(login, password);

        Optional<Reader> reader = readerRepository.queryForSingleResult(specification);

        connectionPool.returnConnection(connection);

        return reader;
    }
}