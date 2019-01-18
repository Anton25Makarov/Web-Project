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

public class LibrarianService extends Service {

    public Optional<Reader> login(String login, String password) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Reader> readerRepository = factory.createReaderRepository();

            SqlSpecification specification = new FindUserByLoginAndPasswordSpecification(login, password);

            return readerRepository.queryForSingleResult(specification);
        }
    }

    public List<Order> takeOrders() throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Order> orderRepository = factory.createOrderRepository();

            SqlSpecification specification = new FindAllSpecification();

            return orderRepository.query(specification);
        }
    }

    public List<Order> takeOrdersToIssue() throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Order> orderRepository = factory.createOrderRepository();

            SqlSpecification specification = new FindOrdersToIssueSpecification();

            return orderRepository.query(specification);
        }
    }

    public void saveOrder(Order order) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Order> orderRepository = factory.createOrderRepository();

            orderRepository.save(order);
        }
    }

    public Optional<Book> getBook(Long bookId) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Book> bookRepository = factory.createBookRepository();

            SqlSpecification specification = new FindBookByIdSpecification(bookId);

            return bookRepository.queryForSingleResult(specification);
        }
    }

    public Optional<Reader> getReader(Long bookId) throws SQLException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Reader> readerRepository = factory.createReaderRepository();

            SqlSpecification specification = new FindByIdSpecification(bookId);

            return readerRepository.queryForSingleResult(specification);
        }
    }
}