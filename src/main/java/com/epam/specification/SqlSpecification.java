package com.epam.specification;

import java.util.Optional;

public interface SqlSpecification<T> {
    Optional<T> specify();

}
