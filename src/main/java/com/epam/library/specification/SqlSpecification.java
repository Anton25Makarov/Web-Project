package com.epam.library.specification;

import java.util.List;

public interface SqlSpecification {
    String toSql();

    List<String> getParameters();
}