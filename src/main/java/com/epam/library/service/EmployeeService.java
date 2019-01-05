package com.epam.library.service;

import com.epam.library.connection.ConnectionPool;
import com.epam.library.model.Author;
import com.epam.library.model.BookGenre;
import com.epam.library.repositpry.AbstractRepository;
import com.epam.library.specification.FindUserByLoginAndPasswordSpecification;
import com.epam.library.specification.SqlSpecification;
import com.epam.library.model.Book;
import com.epam.library.model.Employee;
import com.epam.library.repositpry.RepositoryFactory;
import com.epam.library.specification.FindAllBookSpecification;
import com.epam.library.specification.FindAllSpecification;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeService {

    public Optional<Employee> login(String login, String password)
            throws SQLException, InterruptedException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();// create block finally and add returnConnection
        Connection connection = connectionPool.takeConnection();

        AbstractRepository<Employee> employeeRepository = RepositoryFactory.createEmployeeRepository(connection);

        SqlSpecification specification = new FindUserByLoginAndPasswordSpecification(login, password);

        Optional<Employee> employee = employeeRepository.queryForSingleResult(specification);

        connectionPool.returnConnection(connection);

        return employee;
    }

    public List<Book> takeBooks() throws InterruptedException, SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();// create block finally and add returnConnection
        Connection connection = connectionPool.takeConnection();

        AbstractRepository<Book> bookRepository = RepositoryFactory.createBookRepository(connection);

        SqlSpecification specification = new FindAllBookSpecification();
        List<Book> books = bookRepository.query(specification);

        connectionPool.returnConnection(connection);
        return books;
    }

    public List<BookGenre> takeGenres() throws InterruptedException, SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();// create block finally and add returnConnection
        Connection connection = connectionPool.takeConnection();

        AbstractRepository<BookGenre> genreRepository = RepositoryFactory.createBookGenreRepository(connection);

        SqlSpecification specification = new FindAllSpecification();//????
        List<BookGenre> genres = genreRepository.query(specification);

        connectionPool.returnConnection(connection);
        return genres;
    }

    public List<Author> takeAuthors() throws InterruptedException, SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();// create block finally and add returnConnection
        Connection connection = connectionPool.takeConnection();

        AbstractRepository<Author> authorRepository = RepositoryFactory.createAuthorRepository(connection);

        SqlSpecification specification = new FindAllSpecification();//????
        List<Author> authors = authorRepository.query(specification);

        connectionPool.returnConnection(connection);
        return authors;
    }

    public boolean saveBook(Book book) throws InterruptedException, SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();// create block finally and add returnConnection
        Connection connection = connectionPool.takeConnection();

        AbstractRepository<Book> bookRepository = RepositoryFactory.createBookRepository(connection);

        boolean result = bookRepository.save(book);

        connectionPool.returnConnection(connection);

        return result;
    }

    public boolean saveGenre(BookGenre genre) throws InterruptedException, SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();// create block finally and add returnConnection
        Connection connection = connectionPool.takeConnection();

        AbstractRepository<BookGenre> genreRepository = RepositoryFactory.createBookGenreRepository(connection);

        boolean result = genreRepository.save(genre);

        connectionPool.returnConnection(connection);

        return result;
    }

    public boolean saveAuthor(Author author) throws InterruptedException, SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();// create block finally and add returnConnection
        Connection connection = connectionPool.takeConnection();

        AbstractRepository<Author> authorRepository = RepositoryFactory.createAuthorRepository(connection);

        boolean result = authorRepository.save(author);

        connectionPool.returnConnection(connection);

        return result;
    }

    public boolean removeBook(Book book) throws InterruptedException, SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();// create block finally and add returnConnection
        Connection connection = connectionPool.takeConnection();

        AbstractRepository<Book> bookRepository = RepositoryFactory.createBookRepository(connection);

        boolean result = bookRepository.remove(book);

        connectionPool.returnConnection(connection);

        return result;
    }


}