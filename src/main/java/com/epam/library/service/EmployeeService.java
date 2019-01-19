package com.epam.library.service;

import com.epam.library.exception.RepositoryException;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Employee;
import com.epam.library.repositpry.AbstractRepository;
import com.epam.library.repositpry.RepositoryFactory;
import com.epam.library.specification.FindAllLibrariansSpecification;
import com.epam.library.specification.FindUserByLoginAndPasswordSpecification;
import com.epam.library.specification.FindUserByLoginSpecification;
import com.epam.library.specification.SqlSpecification;

import java.util.List;
import java.util.Optional;

public class EmployeeService { // Check all Parameters !!!!!!!!!

    public Optional<Employee> login(String login, String password) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Employee> employeeRepository = factory.createEmployeeRepository();

            SqlSpecification specification = new FindUserByLoginAndPasswordSpecification(login, password);

            return employeeRepository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void save(Employee employee) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Employee> employeeRepository = factory.createEmployeeRepository();

            employeeRepository.save(employee);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public boolean isEmployeeExist(String login) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Employee> employeeRepository = factory.createEmployeeRepository();

            SqlSpecification specification = new FindUserByLoginSpecification(login);

            return employeeRepository.queryForSingleResult(specification).isPresent();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Employee> getLibrarians() throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Employee> employeeRepository = factory.createEmployeeRepository();

            SqlSpecification specification = new FindAllLibrariansSpecification();

            return employeeRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void remove(Employee employee) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Employee> employeeRepository = factory.createEmployeeRepository();

            employeeRepository.remove(employee);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

}