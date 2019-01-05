package com.epam.library.service;

import com.epam.library.connection.ConnectionPool;
import com.epam.library.model.Reader;
import com.epam.library.repositpry.AbstractRepository;
import com.epam.library.repositpry.RepositoryFactory;
import com.epam.library.specification.FindUserByLoginAndPasswordSpecification;
import com.epam.library.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class ReaderService {

    public Optional<Reader> login(String login, String password)
            throws SQLException, InterruptedException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.takeConnection();

        AbstractRepository readerRepository = RepositoryFactory.createReaderRepository(connection);

        SqlSpecification specification = new FindUserByLoginAndPasswordSpecification(login, password);

        Optional<Reader> reader = readerRepository.queryForSingleResult(specification);

        connectionPool.returnConnection(connection);

        return reader;
    }
}