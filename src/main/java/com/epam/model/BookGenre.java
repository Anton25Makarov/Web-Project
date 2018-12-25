package com.epam.model;

public class BookGenre extends Entity {
    private String genre;

    public BookGenre(Long id, String genre) {
        super(id);
        this.genre = genre;
    }

    public BookGenre(String genre) {
        this.genre = genre;
    }

    public BookGenre(Long id) {
        super(id);
    }


    public String getGenre() {
        return genre;
    }

}
