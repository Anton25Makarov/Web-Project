package com.epam.library.builder;

import com.epam.library.model.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Interface representing a builder. Builders are objects that are used to
 * construct other objects.
 *
 * @param <T> the type of element to build
 */
public interface Builder<T extends Entity> {

    /**
     * Build and return object
     *
     * @param resultSet the result of selecting, generic by {@link java.sql.PreparedStatement}.
     * @return the object extended {@link com.epam.library.model.Entity}
     * @throws SQLException if a database access error occurs;
     */
    T build(ResultSet resultSet) throws SQLException;
}
