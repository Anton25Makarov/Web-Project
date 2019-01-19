package com.epam.library.service;

import com.epam.library.exception.RepositoryException;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Order;
import com.epam.library.repositpry.AbstractRepository;
import com.epam.library.repositpry.RepositoryFactory;
import com.epam.library.specification.*;

import java.util.List;
import java.util.Optional;

public class OrderService {

    public List<Order> getOrders() throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Order> orderRepository = factory.createOrderRepository();

            SqlSpecification specification = new FindAllSpecification();

            return orderRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Order> getOrdersToIssue() throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Order> orderRepository = factory.createOrderRepository();

            SqlSpecification specification = new FindOrdersToIssueSpecification();

            return orderRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void save(Order order) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Order> orderRepository = factory.createOrderRepository();

            orderRepository.save(order);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Order> getReaderOrders(Long readerId) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Order> orderRepository = factory.createOrderRepository();

            SqlSpecification specification = new FindCurrentReaderBooksSpecification(readerId);

            return orderRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Order> getOrder(Long bookId) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Order> orderRepository = factory.createOrderRepository();

            SqlSpecification specification = new FindByIdSpecification(bookId);

            return orderRepository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}