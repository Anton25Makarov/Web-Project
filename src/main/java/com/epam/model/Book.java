package com.epam.model;

public class Book extends Entity {
    private String title;
    private int year;
    private int count;
    private Author author;
    private String genre;

    public Book(Long id, String title, int year, int count, Author author, String genre) {
        super(id);
        this.title = title;
        this.year = year;
        this.count = count;
        this.author = author;
        this.genre = genre;
    }

    public Book(String title, int year, int count, Author author, String genre) {
        this.title = title;
        this.year = year;
        this.count = count;
        this.author = author;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getCount() {
        return count;
    }

    public Author getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }
}
