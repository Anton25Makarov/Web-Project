package com.epam.library.repositpry;

import com.epam.library.exception.RepositoryException;
import com.epam.library.model.Entity;
import com.epam.library.specification.SqlSpecification;

import java.util.List;
import java.util.Optional;

/**
 * Interface for interacting with database
 *
 * @param <T> the type of element
 */
public interface Repository<T extends Entity> {

    /**
     * Generate sql query, depending on specification, to database and return the result.
     *
     * @param sqlSpecification for perform various requests, depending on a specific instance.
     * @return List of objects generated after execute query.
     * @throws RepositoryException when sql query is not valid.
     */
    List<T> query(SqlSpecification sqlSpecification) throws RepositoryException;

    /**
     * Generate sql query, depending on specification, to database and return the result.
     *
     * @param sqlSpecification for perform various requests, depending on a specific instance.
     * @return an optional object generated after execute query.
     * @throws RepositoryException when sql query is not valid.
     */
    Optional<T> queryForSingleResult(SqlSpecification sqlSpecification) throws RepositoryException;

    /**
     * @param entity is an object needs to save in database
     * @throws RepositoryException when sql query is not valid.
     */
    void save(T entity) throws RepositoryException;

    /**
     * @param entity is an object needs to remove from database
     * @throws RepositoryException when sql query is not valid.
     */
    void remove(T entity) throws RepositoryException;
}
