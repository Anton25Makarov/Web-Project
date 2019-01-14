package com.epam.library.specification;

import java.util.ArrayList;
import java.util.List;

public class FindBookInStockByIdSpecification implements SqlSpecification {
    private static final String QUERY_JOINS =
            "join book_author ba on book.book_author_id = ba.id\n" +
                    "join genre_catalog gc on book.genre_catalog_id = gc.id\n" +
                    "where count >= 1     and\n" +
                    "      book.id = ?;";

    private Long bookId;

    public FindBookInStockByIdSpecification(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toSql() {
        return QUERY_JOINS;
    }

    @Override
    public List<String> getParameters() {
        List<String> parameters = new ArrayList<>();

        parameters.add(String.valueOf(bookId));

        return parameters;
    }
}
