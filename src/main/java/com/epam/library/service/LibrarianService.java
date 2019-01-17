package com.epam.library.service;

import com.epam.library.model.Order;
import com.epam.library.model.Reader;
import com.epam.library.repositpry.AbstractRepository;
import com.epam.library.repositpry.RepositoryFactory;
import com.epam.library.specification.FindAllSpecification;
import com.epam.library.specification.FindOrdersToIssueSpecification;
import com.epam.library.specification.FindUserByLoginAndPasswordSpecification;
import com.epam.library.specification.SqlSpecification;

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
}