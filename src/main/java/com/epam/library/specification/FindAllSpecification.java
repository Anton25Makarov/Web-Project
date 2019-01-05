package com.epam.library.specification;

import java.util.ArrayList;
import java.util.List;

public class FindAllSpecification implements SqlSpecification {
    private static final String EMPTY_CONDITION = ";";

    @Override
    public String toSql() {
        return EMPTY_CONDITION;
    }

    @Override
    public List<String> getParameters() {
        return new ArrayList<>();
    }
}
