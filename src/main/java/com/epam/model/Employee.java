package com.epam.model;

public class Employee extends Entity {
    private String name;
    private String surname;
    private String login;
    private String password;
    private boolean admin;

    public Employee(String name, String surname, String login, String password, boolean admin) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.admin = admin;
    }
}
