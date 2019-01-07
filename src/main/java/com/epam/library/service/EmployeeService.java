package com.epam.library.service;

import com.epam.library.model.*;
import com.epam.library.repositpry.AbstractRepository;
import com.epam.library.specification.*;
import com.epam.library.repositpry.RepositoryFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeService extends Service { // Check all Parameters !!!!!!!!!

    public EmployeeService() throws InterruptedException {
        super();
    }

    public Optional<Employee> login(String login, String password) throws SQLException {
        AbstractRepository<Employee> employeeRepository = RepositoryFactory.createEmployeeRepository(connection);

        SqlSpecification specification = new FindUserByLoginAndPasswordSpecification(login, password);

        return employeeRepository.queryForSingleResult(specification);
    }

    public List<Book> takeBooks() throws SQLException {
        AbstractRepository<Book> bookRepository = RepositoryFactory.createBookRepository(connection);

        SqlSpecification specification = new FindAllBookSpecification();

        return bookRepository.query(specification);
    }

    public List<BookGenre> takeGenres() throws SQLException {
        AbstractRepository<BookGenre> genreRepository = RepositoryFactory.createBookGenreRepository(connection);

        SqlSpecification specification = new FindAllSpecification();

        return genreRepository.query(specification);
    }

    public List<Author> takeAuthors() throws SQLException {
        AbstractRepository<Author> authorRepository = RepositoryFactory.createAuthorRepository(connection);

        SqlSpecification specification = new FindAllSpecification();

        return authorRepository.query(specification);
    }

    public boolean saveBook(Book book) throws SQLException {
        AbstractRepository<Book> bookRepository = RepositoryFactory.createBookRepository(connection);

        return bookRepository.save(book);
    }

    public boolean saveGenre(BookGenre genre) throws SQLException {
        AbstractRepository<BookGenre> genreRepository = RepositoryFactory.createBookGenreRepository(connection);

        return genreRepository.save(genre);
    }

    public boolean saveAuthor(Author author) throws SQLException {
        AbstractRepository<Author> authorRepository = RepositoryFactory.createAuthorRepository(connection);

        return authorRepository.save(author);
    }

    public boolean saveEmployee(Employee employee) throws SQLException {
        AbstractRepository<Employee> employeeRepository = RepositoryFactory.createEmployeeRepository(connection);

        return employeeRepository.save(employee);
    }

    public boolean removeBook(Book book) throws SQLException {
        AbstractRepository<Book> bookRepository = RepositoryFactory.createBookRepository(connection);

        return bookRepository.remove(book);
    }

    public boolean isEmployeeExist(String login) throws SQLException {
        AbstractRepository<Employee> employeeRepository = RepositoryFactory.createEmployeeRepository(connection);

        SqlSpecification specification = new FindUserByLoginSpecification(login);

        return employeeRepository.queryForSingleResult(specification).isPresent();
    }

    public boolean isReaderExist(String login) throws SQLException {
        AbstractRepository<Reader> readerRepository = RepositoryFactory.createReaderRepository(connection);

        SqlSpecification specification = new FindUserByLoginSpecification(login);

        return readerRepository.queryForSingleResult(specification).isPresent();
    }

    public List<Employee> takeLibrarians() throws SQLException {
        AbstractRepository<Employee> employeeRepository = RepositoryFactory.createEmployeeRepository(connection);

        SqlSpecification specification = new FindAllLibrariansSpecification();

        return employeeRepository.query(specification);
    }

    public boolean removeLibrarian(Employee employee) throws SQLException {
        AbstractRepository<Employee> employeeRepository = RepositoryFactory.createEmployeeRepository(connection);

        return employeeRepository.remove(employee);
    }

    public List<Reader> takeReaders() throws SQLException {
        AbstractRepository<Reader> readerRepository = RepositoryFactory.createReaderRepository(connection);

        SqlSpecification specification = new FindAllSpecification();

        return readerRepository.query(specification);
    }

    public boolean saveReader(Reader reader) throws SQLException {
        AbstractRepository<Reader> readerRepository = RepositoryFactory.createReaderRepository(connection);

        return readerRepository.save(reader);
    }

    public boolean removeReader(Reader reader) throws SQLException {
        AbstractRepository<Reader> readerRepository = RepositoryFactory.createReaderRepository(connection);

        return readerRepository.remove(reader);
    }
}