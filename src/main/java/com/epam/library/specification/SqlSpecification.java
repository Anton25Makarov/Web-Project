package com.epam.library.specification;

import java.util.List;

/**
 * Is used in {@link com.epam.library.repositpry.Repository} to form a specific sql query
 */
public interface SqlSpecification {
    /**
     * @return specifying conditions to for executing sql queries
     */
    String toSql();

    /**
     * @return List of parameters, that will replace '?' in PreparedStatement line.
     */
    List<String> getParameters();
}