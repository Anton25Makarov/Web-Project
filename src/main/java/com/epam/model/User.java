package com.epam.model;

import java.io.Serializable;

public class User extends Entity implements Serializable {
    private String name;
    private String surname;
    private String login;
    private String password;
    private boolean admin;

    private String role;

    public User(String name, String surname, String login, String password, boolean admin) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
