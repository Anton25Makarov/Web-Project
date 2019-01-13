package com.epam.library.specification;

import java.util.ArrayList;
import java.util.List;

public class FindByIdSpecification implements SqlSpecification {
    private static final String QUERY_CONDITION = " where id = ?;";

    private Long id;

    public FindByIdSpecification(Long id) {
        this.id = id;
    }

    @Override
    public String toSql() {
        return QUERY_CONDITION;
    }

    @Override
    public List<String> getParameters() {
        List<String> parameters = new ArrayList<>();

        parameters.add(String.valueOf(id));

        return parameters;
    }
}
