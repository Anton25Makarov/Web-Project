package com.epam.library.model;

import java.util.Date;

public class Order extends Entity {
    private boolean inReadingRoom;
    private Date takingDate;
    private Date returnDate;
    private Book book;
    private Reader reader;

    public Order(Long id, boolean inReadingRoom, Date takingDate, Date returnDate, Book book, Reader reader) {
        super(id);
        this.inReadingRoom = inReadingRoom;
        this.takingDate = takingDate;
        this.returnDate = returnDate;
        this.book = book;
        this.reader = reader;
    }

    public Order(boolean inReadingRoom, Date takingDate, Date returnDate, Book book, Reader reader) {
        this.inReadingRoom = inReadingRoom;
        this.takingDate = takingDate;
        this.returnDate = returnDate;
        this.book = book;
        this.reader = reader;
    }

    public Order(Long id) {
        super(id);
    }

    public boolean isInReadingRoom() {
        return inReadingRoom;
    }

    public Date getTakingDate() {
        return takingDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
