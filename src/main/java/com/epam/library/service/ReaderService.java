package com.epam.library.service;

import com.epam.library.exception.RepositoryException;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Reader;
import com.epam.library.repositpry.AbstractRepository;
import com.epam.library.repositpry.RepositoryFactory;
import com.epam.library.specification.*;

import java.util.List;
import java.util.Optional;

public class ReaderService {

    public Optional<Reader> login(String login, String password) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Reader> readerRepository = factory.createReaderRepository();

            SqlSpecification specification = new FindUserByLoginAndPasswordSpecification(login, password);

            return readerRepository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Reader> getReaders() throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Reader> readerRepository = factory.createReaderRepository();

            SqlSpecification specification = new FindAllSpecification();

            return readerRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void save(Reader reader) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Reader> readerRepository = factory.createReaderRepository();

            readerRepository.save(reader);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void remove(Reader reader) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Reader> readerRepository = factory.createReaderRepository();

            readerRepository.remove(reader);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public boolean isReaderExist(String login) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Reader> readerRepository = factory.createReaderRepository();

            SqlSpecification specification = new FindUserByLoginSpecification(login);

            return readerRepository.queryForSingleResult(specification).isPresent();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Reader> getReader(Long bookId) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Reader> readerRepository = factory.createReaderRepository();

            SqlSpecification specification = new FindByIdSpecification(bookId);

            return readerRepository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}