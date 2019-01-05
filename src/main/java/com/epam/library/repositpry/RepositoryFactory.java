package com.epam.library.repositpry;

import java.sql.Connection;

public class RepositoryFactory {
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

}