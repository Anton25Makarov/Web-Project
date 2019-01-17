package com.epam.library.repositpry;

import com.epam.library.connection.ConnectionPool;

import java.sql.Connection;

public class RepositoryFactory implements AutoCloseable {

    private Connection connection;
    private ConnectionPool connectionPool;

    public RepositoryFactory() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
    }

    public BookRepository createBookRepository() {
        return new BookRepository(connection);
    }

    public EmployeeRepository createEmployeeRepository() {
        return new EmployeeRepository(connection);
    }

    public ReaderRepository createReaderRepository() {
        return new ReaderRepository(connection);
    }

    public GenreRepository createBookGenreRepository() {
        return new GenreRepository(connection);
    }

    public AuthorRepository createAuthorRepository() {
        return new AuthorRepository(connection);
    }

    public OrderRepository createOrderRepository() {
        return new OrderRepository(connection);
    }

    @Override
    public void close() {
        connectionPool.returnConnection(connection);
    }
}