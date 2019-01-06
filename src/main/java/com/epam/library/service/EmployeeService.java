package com.epam.library.service;

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

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeService extends Service {

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

    public boolean removeBook(Book book) throws SQLException {
        AbstractRepository<Book> bookRepository = RepositoryFactory.createBookRepository(connection);

        return bookRepository.remove(book);
    }
}