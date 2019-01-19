package com.epam.library.model;

import java.io.Serializable;

public class Reader extends Entity implements Serializable {
    private static final long serialVersionUID = 5055250724556125522L;

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

    public Reader(Long id, String login) {
        super(id);
        this.login = login;
    }

    public Reader(String login) {
        this.login = login;
    }

    public Reader(Long id) {
        super(id);
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
