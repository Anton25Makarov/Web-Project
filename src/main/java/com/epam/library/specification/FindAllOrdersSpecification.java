package com.epam.library.specification;

import java.util.ArrayList;
import java.util.List;

public class FindAllOrdersSpecification implements SqlSpecification {
    private static final String QUERY_CONDITION = "\njoin reader r on `order`.reader_id = r.id\n" +
            "       join book b on `order`.book_id = b.id\n" +
            "       join book_author ba on b.book_author_id = ba.id\n" +
            "       join genre_catalog gc on b.genre_catalog_id = gc.id;";

    @Override
    public String toSql() {
        return QUERY_CONDITION;
    }

    @Override
    public List<String> getParameters() {
        return new ArrayList<>();
    }
}
