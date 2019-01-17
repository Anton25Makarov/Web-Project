package com.epam.library.service;

import com.epam.library.model.Book;
import com.epam.library.model.Order;
import com.epam.library.model.Reader;
import com.epam.library.repositpry.AbstractRepository;
import com.epam.library.repositpry.RepositoryFactory;
import com.epam.library.specification.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReaderService extends Service {

    public Optional<Reader> login(String login, String password) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Reader> readerRepository = factory.createReaderRepository();

            SqlSpecification specification = new FindUserByLoginAndPasswordSpecification(login, password);

            return readerRepository.queryForSingleResult(specification);
        }
    }

    public Optional<Book> getBook(Long bookId) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Book> bookRepository = factory.createBookRepository();

            SqlSpecification specification = new FindBookByIdSpecification(bookId);

            return bookRepository.queryForSingleResult(specification);
        }
    }

    public List<Book> getBooks(String title, String authorName, String authorSurname, String bookGenre)
            throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Book> bookRepository = factory.createBookRepository();

            SqlSpecification specification = new FindBooksInStockSpecification(title, authorName, authorSurname, bookGenre);

            return bookRepository.query(specification);
        }
    }

    public Optional<Book> getBookInStoke(Long bookId) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Book> bookRepository = factory.createBookRepository();

            SqlSpecification specification = new FindBookInStockByIdSpecification(bookId);

            return bookRepository.queryForSingleResult(specification);
        }
    }

    public void saveBook(Book book) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Book> bookRepository = factory.createBookRepository();

            bookRepository.save(book);
        }
    }

    public void saveOrder(Order order) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Order> orderRepository = factory.createOrderRepository();

            orderRepository.save(order);
        }
    }

    public List<Order> getReaderOrders(Long readerId) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Order> orderRepository = factory.createOrderRepository();

            SqlSpecification specification = new FindCurrentReaderBooksSpecification(readerId);

            return orderRepository.query(specification);
        }
    }

    public Optional<Order> getOrder(Long bookId) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Order> orderRepository = factory.createOrderRepository();

            SqlSpecification specification = new FindByIdSpecification(bookId);

            return orderRepository.queryForSingleResult(specification);
        }
    }
}