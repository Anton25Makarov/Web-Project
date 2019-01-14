package com.epam.library.specification;

import java.util.ArrayList;
import java.util.List;

public class FindAllBooksInStockSpecification implements SqlSpecification {
    private static final String QUERY_JOINS =
            "join book_author ba on book.book_author_id = ba.id\n" +
                    "join genre_catalog gc on book.genre_catalog_id = gc.id\n" +
                    "where count >= 1;";

    @Override
    public String toSql() {
        return QUERY_JOINS;
    }

    @Override
    public List<String> getParameters() {
        return new ArrayList<>();
    }
}
