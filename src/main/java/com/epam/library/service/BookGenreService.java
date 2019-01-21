package com.epam.library.service;

import com.epam.library.exception.RepositoryException;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.BookGenre;
import com.epam.library.repositpry.AbstractRepository;
import com.epam.library.repositpry.RepositoryFactory;
import com.epam.library.specification.FindAllSpecification;
import com.epam.library.specification.FindByIdSpecification;
import com.epam.library.specification.SqlSpecification;

import java.util.List;
import java.util.Optional;

public class BookGenreService {

    public List<BookGenre> getGenres() throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<BookGenre> genreRepository = factory.createBookGenreRepository();

            SqlSpecification specification = new FindAllSpecification();

            return genreRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void save(BookGenre genre) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<BookGenre> genreRepository = factory.createBookGenreRepository();

            genreRepository.save(genre);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<BookGenre> getBookGenre(Long bookId) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<BookGenre> genreRepository = factory.createBookGenreRepository();

            SqlSpecification specification = new FindByIdSpecification(bookId);

            return genreRepository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}