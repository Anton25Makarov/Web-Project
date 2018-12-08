package com.epam.specification;

import com.epam.model.Reader;

import java.util.Optional;

public class FindReaderByLoginAndPasswordSpecification implements SqlSpecification<Reader> {
    private String login;
    private String password;

    public FindReaderByLoginAndPasswordSpecification(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public Optional<Reader> specify() {
        // find employee in BD
        Reader reader = new Reader(1L, "Johnatan", "Smith JR",
                "J54321", "ytrewq", "+3752945992585");
        return Optional.of(reader);
    }

    @Override
    public String toSql() {
        return null;
    }
}
