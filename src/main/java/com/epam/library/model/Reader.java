package com.epam.library.model;

import java.io.Serializable;

public class Reader extends Entity implements Serializable {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String telephoneNumber;

    public Reader(Long id, String name, String surname, String login, String password, String telephoneNumber) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
    }

    public Reader(String name, String surname, String login, String password, String telephoneNumber) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
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

    public String getTelephoneNumber() {
        return telephoneNumber;
    }
}