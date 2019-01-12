package com.epam.library.specification;

import java.util.ArrayList;
import java.util.List;

public class FindOrdersToIssueSpecification implements SqlSpecification {
    private static final String QUERY_CONDITION = "where taking_date is null;";

    @Override
    public String toSql() {
        return QUERY_CONDITION;
    }

    @Override
    public List<String> getParameters() {
        return new ArrayList<>();
    }
}
