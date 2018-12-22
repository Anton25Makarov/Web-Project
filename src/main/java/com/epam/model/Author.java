package com.epam.model;

public class Author extends Entity {
    private String name;
    private String surname;

    public Author(Long id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
