package com.epam.model;

import java.io.Serializable;

public class Reader extends Entity implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String telephoneNumber;

    public Reader(Long id, String name, String surname, String login, String password, String telephoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
    }
}
