package com.epam.specification;

import java.util.ArrayList;
import java.util.List;

public class FindUserByLoginAndPasswordSpecification implements SqlSpecification {
    private static final String QUERY_CONDITION = "where login = ? and password = ?;";

    private String login;
    private String password;

    public FindUserByLoginAndPasswordSpecification(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toSql() {
        return QUERY_CONDITION;
    }

    @Override
    public List<String> getParameters() {
        List<String> parameters = new ArrayList<>();

        parameters.add(login);
        parameters.add(password);

        return parameters;
    }
}
