package com.epam.library.specification;

import java.util.ArrayList;
import java.util.List;

public class FindUserByLoginSpecification implements SqlSpecification {
    private static final String QUERY_CONDITION = "where login = ?;";

    private String login;

    public FindUserByLoginSpecification(String login) {
        this.login = login;
    }

    @Override
    public String toSql() {
        return QUERY_CONDITION;
    }

    @Override
    public List<String> getParameters() {
        List<String> parameters = new ArrayList<>();

        parameters.add(login);

        return parameters;
    }
}
