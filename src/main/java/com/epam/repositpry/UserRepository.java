package com.epam.repositpry;

import com.epam.model.User;

import java.util.List;
import java.util.Optional;

public class UserRepository implements Repository<User> {

    @Override
    public List<User> query() {
        return null;
    }

    @Override
    public Optional<User> queryForSingleResult() {
        return Optional.empty();
    }


}
