package com.epam.model;

import java.io.Serializable;

public class Employee extends Entity implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private boolean admin;

    public Employee(Long id, String name, String surname, String login, String password, boolean admin) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public Long getId() {
        return id;
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
}
