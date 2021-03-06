package com.epam.library.model;

public class Book extends Entity {
    private String title;
    private int year;
    private int count;
    private Author author;
    private BookGenre genre;

    public Book(Long id, String title, int year, int count, Author author, BookGenre genre) {
        super(id);
        this.title = title;
        this.year = year;
        this.count = count;
        this.author = author;
        this.genre = genre;
    }

    public Book(String title, int year, int count, Author author, BookGenre genre) {
        this.title = title;
        this.year = year;
        this.count = count;
        this.author = author;
        this.genre = genre;
    }

    public Book(String title, Author author, BookGenre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Book(Long id) {
        super(id);
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

    public void setCount(int count) {
        this.count = count;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }
}
