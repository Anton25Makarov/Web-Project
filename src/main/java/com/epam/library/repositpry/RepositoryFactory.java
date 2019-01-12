package com.epam.library.repositpry;

import java.sql.Connection;

public class RepositoryFactory {

    private RepositoryFactory() {
    }

    public static EmployeeRepository createEmployeeRepository(Connection connection) {
        return new EmployeeRepository(connection);
    }

    public static ReaderRepository createReaderRepository(Connection connection) {
        return new ReaderRepository(connection);
    }

    public static BookRepository createBookRepository(Connection connection) {
        return new BookRepository(connection);
    }

    public static GenreRepository createBookGenreRepository(Connection connection) {
        return new GenreRepository(connection);
    }

    public static AuthorRepository createAuthorRepository(Connection connection) {
        return new AuthorRepository(connection);
    }

    public static OrderRepository createOrderRepository(Connection connection) {
        return new OrderRepository(connection);
    }

}