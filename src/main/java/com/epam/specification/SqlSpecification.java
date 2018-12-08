package com.epam.specification;

import com.epam.model.Entity;

import java.util.Optional;

public interface SqlSpecification<T extends Entity> {
    Optional<T> specify();
    String toSql();
}
