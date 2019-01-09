package com.epam.library.service;

import com.epam.library.model.Reader;
import com.epam.library.repositpry.AbstractRepository;
import com.epam.library.repositpry.RepositoryFactory;
import com.epam.library.specification.FindUserByLoginAndPasswordSpecification;
import com.epam.library.specification.SqlSpecification;

import java.sql.SQLException;
import java.util.Optional;

public class ReaderService extends Service {

    public ReaderService() throws InterruptedException {
    }

    public Optional<Reader> login(String login, String password) throws SQLException {
        AbstractRepository<Reader> readerRepository = RepositoryFactory.createReaderRepository(connection);

        SqlSpecification specification = new FindUserByLoginAndPasswordSpecification(login, password);

        return readerRepository.queryForSingleResult(specification);
    }
}