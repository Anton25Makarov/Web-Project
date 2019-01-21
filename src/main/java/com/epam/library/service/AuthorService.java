package com.epam.library.service;

import com.epam.library.exception.RepositoryException;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Author;
import com.epam.library.repositpry.AbstractRepository;
import com.epam.library.repositpry.RepositoryFactory;
import com.epam.library.specification.FindAllSpecification;
import com.epam.library.specification.FindByIdSpecification;
import com.epam.library.specification.SqlSpecification;

import java.util.List;
import java.util.Optional;

public class AuthorService {

    public void save(Author author) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Author> authorRepository = factory.createAuthorRepository();

            authorRepository.save(author);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Author> getAuthors() throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Author> authorRepository = factory.createAuthorRepository();

            SqlSpecification specification = new FindAllSpecification();

            return authorRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Author> getAuthor(Long bookId) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Author> authorRepository = factory.createAuthorRepository();

            SqlSpecification specification = new FindByIdSpecification(bookId);

            return authorRepository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}