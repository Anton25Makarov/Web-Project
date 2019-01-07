package com.epam.library.specification;

import java.util.ArrayList;
import java.util.List;

public class FindAllLibrariansSpecification implements SqlSpecification {
    private static final String QUERY_CONDITION = "where is_admin = false";

    @Override
    public String toSql() {
        return QUERY_CONDITION;
    }

    @Override
    public List<String> getParameters() {
        return new ArrayList<>();
    }
}
