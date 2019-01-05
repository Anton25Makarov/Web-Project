package com.epam.library.model;

import java.io.Serializable;

public class Employee extends Entity implements Serializable {
    private String name;
    private String surname;
    private String login;
    private String password;
    private boolean admin;

    public Employee(Long id, String name, String surname, String login, String password, boolean admin) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public Employee(String name, String surname, String login, String password, boolean admin) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public Long getId() {
        return super.getId();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }
}
