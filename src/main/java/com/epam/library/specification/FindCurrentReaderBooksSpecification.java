package com.epam.library.specification;

import java.util.ArrayList;
import java.util.List;

public class FindCurrentReaderBooksSpecification implements SqlSpecification {
    private static final String QUERY_JOINS = "\nwhere reader_id = ?\n " +
            "and return_date is null;";

    private Long readerId;

    public FindCurrentReaderBooksSpecification(Long readerId) {
        this.readerId = readerId;
    }

    @Override
    public String toSql() {
        return QUERY_JOINS;
    }

    @Override
    public List<String> getParameters() {
        List<String> parameters = new ArrayList<>();

        parameters.add(String.valueOf(readerId));

        return parameters;
    }
}
