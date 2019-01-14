package com.epam.library.specification;

import java.util.ArrayList;
import java.util.List;

public class FindBooksInStockSpecification implements SqlSpecification {
    public static final String ANY_CHARACTERS_PATTERN = "%";
    private static final String QUERY_JOINS =
            "join book_author ba on book.book_author_id = ba.id\n" +
                    "join genre_catalog gc on book.genre_catalog_id = gc.id\n" +
                    "where count >= 1     and\n" +
                    "      title   like ? and\n" +
                    "      name    like ? and\n" +
                    "      surname like ? and " +
                    "      genre   like ?;";

    private String bookTitle;
    private String authorName;
    private String authorSurname;
    private String bookGenre;

    public FindBooksInStockSpecification(String bookTitle, String authorName, String authorSurname, String bookGenre) {
        this.bookTitle = ANY_CHARACTERS_PATTERN + bookTitle + ANY_CHARACTERS_PATTERN;
        this.authorName = ANY_CHARACTERS_PATTERN + authorName + ANY_CHARACTERS_PATTERN;
        this.authorSurname = ANY_CHARACTERS_PATTERN + authorSurname + ANY_CHARACTERS_PATTERN;
        this.bookGenre = ANY_CHARACTERS_PATTERN + bookGenre + ANY_CHARACTERS_PATTERN;
    }

    @Override
    public String toSql() {
        return QUERY_JOINS;
    }

    @Override
    public List<String> getParameters() {
        List<String> parameters = new ArrayList<>();

        parameters.add(bookTitle);
        parameters.add(authorName);
        parameters.add(authorSurname);
        parameters.add(bookGenre);

        return parameters;
    }
}
