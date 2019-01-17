package com.epam.library.service;

import com.epam.library.model.*;
import com.epam.library.repositpry.AbstractRepository;
import com.epam.library.specification.*;
import com.epam.library.repositpry.RepositoryFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeService extends Service { // Check all Parameters !!!!!!!!!

    public Optional<Employee> login(String login, String password) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Employee> employeeRepository = factory.createEmployeeRepository();

            SqlSpecification specification = new FindUserByLoginAndPasswordSpecification(login, password);

            return employeeRepository.queryForSingleResult(specification);
        }
    }

    public List<Book> takeBooks() throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Book> bookRepository = factory.createBookRepository();

            SqlSpecification specification = new FindAllBookSpecification();

            return bookRepository.query(specification);
        }
    }

    public List<Book> takeBooksInStock() throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Book> bookRepository = factory.createBookRepository();

            SqlSpecification specification = new FindAllBooksInStockSpecification();

            return bookRepository.query(specification);
        }
    }

    public List<BookGenre> takeGenres() throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<BookGenre> genreRepository = factory.createBookGenreRepository();

            SqlSpecification specification = new FindAllSpecification();

            return genreRepository.query(specification);
        }
    }

    public List<Author> takeAuthors() throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Author> authorRepository = factory.createAuthorRepository();

            SqlSpecification specification = new FindAllSpecification();

            return authorRepository.query(specification);
        }
    }

    public void saveBook(Book book) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Book> bookRepository = factory.createBookRepository();

            bookRepository.save(book);
        }
    }

    public void saveGenre(BookGenre genre) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<BookGenre> genreRepository = factory.createBookGenreRepository();

            genreRepository.save(genre);
        }
    }

    public void saveAuthor(Author author) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Author> authorRepository = factory.createAuthorRepository();

            authorRepository.save(author);
        }
    }

    public void saveEmployee(Employee employee) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Employee> employeeRepository = factory.createEmployeeRepository();

            employeeRepository.save(employee);
        }
    }

    public void removeBook(Book book) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Book> bookRepository = factory.createBookRepository();

            bookRepository.remove(book);
        }
    }

    public boolean isEmployeeExist(String login) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Employee> employeeRepository = factory.createEmployeeRepository();

            SqlSpecification specification = new FindUserByLoginSpecification(login);

            return employeeRepository.queryForSingleResult(specification).isPresent();
        }
    }

    public boolean isReaderExist(String login) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Reader> readerRepository = factory.createReaderRepository();

            SqlSpecification specification = new FindUserByLoginSpecification(login);

            return readerRepository.queryForSingleResult(specification).isPresent();
        }
    }

    public List<Employee> takeLibrarians() throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Employee> employeeRepository = factory.createEmployeeRepository();

            SqlSpecification specification = new FindAllLibrariansSpecification();

            return employeeRepository.query(specification);
        }
    }

    public void removeLibrarian(Employee employee) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Employee> employeeRepository = factory.createEmployeeRepository();

            employeeRepository.remove(employee);
        }
    }

    public List<Reader> takeReaders() throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Reader> readerRepository = factory.createReaderRepository();

            SqlSpecification specification = new FindAllSpecification();

            return readerRepository.query(specification);
        }
    }

    public void saveReader(Reader reader) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Reader> readerRepository = factory.createReaderRepository();

            readerRepository.save(reader);
        }
    }

    public void removeReader(Reader reader) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Reader> readerRepository = factory.createReaderRepository();

            readerRepository.remove(reader);
        }
    }

    public boolean isBookInStock(Long bookId) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Book> bookRepository = factory.createBookRepository();

            SqlSpecification specification = new FindByIdSpecification(bookId);

            return bookRepository.queryForSingleResult(specification).isPresent();
        }
    }
}