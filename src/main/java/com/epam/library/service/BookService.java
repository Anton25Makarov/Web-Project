package com.epam.library.service;

import com.epam.library.exception.RepositoryException;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Book;
import com.epam.library.repositpry.AbstractRepository;
import com.epam.library.repositpry.RepositoryFactory;
import com.epam.library.specification.*;

import java.util.List;
import java.util.Optional;

public class BookService {

    public List<Book> getBooks() throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Book> bookRepository = factory.createBookRepository();

            SqlSpecification specification = new FindAllBookSpecification();

            return bookRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void save(Book book) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Book> bookRepository = factory.createBookRepository();

            bookRepository.save(book);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void remove(Book book) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Book> bookRepository = factory.createBookRepository();

            bookRepository.remove(book);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Book> getBook(Long bookId) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Book> bookRepository = factory.createBookRepository();

            SqlSpecification specification = new FindBookByIdSpecification(bookId);

            return bookRepository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Book> getBooksInStoke(String title, String authorName, String authorSurname, String bookGenre)
            throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Book> bookRepository = factory.createBookRepository();

            SqlSpecification specification = new FindBooksInStockSpecification(title, authorName, authorSurname, bookGenre);

            return bookRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Book> getBookInStoke(Long bookId) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            AbstractRepository<Book> bookRepository = factory.createBookRepository();

            SqlSpecification specification = new FindBookInStockByIdSpecification(bookId);

            return bookRepository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Book> getBooksInStock() throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {

            AbstractRepository<Book> bookRepository = factory.createBookRepository();

            SqlSpecification specification = new FindAllBooksInStockSpecification();

            return bookRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}