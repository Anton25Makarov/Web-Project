package com.epam.library.service;

import com.epam.library.model.Book;
import com.epam.library.model.Order;
import com.epam.library.model.Reader;
import com.epam.library.repositpry.AbstractRepository;
import com.epam.library.repositpry.RepositoryFactory;
import com.epam.library.specification.*;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReaderService extends Service {

    public ReaderService() throws InterruptedException {
    }

    public Optional<Reader> login(String login, String password) throws SQLException {
        AbstractRepository<Reader> readerRepository = RepositoryFactory.createReaderRepository(connection);

        SqlSpecification specification = new FindUserByLoginAndPasswordSpecification(login, password);

        return readerRepository.queryForSingleResult(specification);
    }

    public Optional<Book> getBook(Long bookId) throws SQLException {
        AbstractRepository<Book> bookRepository = RepositoryFactory.createBookRepository(connection);

        SqlSpecification specification = new FindBookByIdSpecification(bookId);

        return bookRepository.queryForSingleResult(specification);
    }

    public List<Book> getBooks(String title, String authorName, String authorSurname, String bookGenre)
            throws SQLException {
        AbstractRepository<Book> bookRepository = RepositoryFactory.createBookRepository(connection);

        SqlSpecification specification = new FindBooksInStockSpecification(title, authorName, authorSurname, bookGenre);

        return bookRepository.query(specification);
    }

    public Optional<Book> getBookInStoke(Long bookId) throws SQLException {
        AbstractRepository<Book> bookRepository = RepositoryFactory.createBookRepository(connection);

        SqlSpecification specification = new FindBookInStockByIdSpecification(bookId);

        return bookRepository.queryForSingleResult(specification);
    }

    public boolean saveBook(Book book) throws SQLException {
        AbstractRepository<Book> bookRepository = RepositoryFactory.createBookRepository(connection);

        return bookRepository.save(book);
    }

    public void saveOrder(Order order) throws SQLException {
        AbstractRepository<Order> orderRepository = RepositoryFactory.createOrderRepository(connection);

        orderRepository.save(order);
    }

    public List<Order> getReaderOrders(Long readerId) throws SQLException {
        AbstractRepository<Order> orderRepository = RepositoryFactory.createOrderRepository(connection);

        SqlSpecification specification = new FindCurrentReaderBooksSpecification(readerId);

        return orderRepository.query(specification);
    }

    public Optional<Order> getOrder(Long bookId) throws SQLException {
        AbstractRepository<Order> orderRepository = RepositoryFactory.createOrderRepository(connection);

        SqlSpecification specification = new FindByIdSpecification(bookId);

        return orderRepository.queryForSingleResult(specification);
    }
}