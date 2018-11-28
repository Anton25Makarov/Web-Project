package com.epam.repositpry;

import com.epam.logic.Entity;
import com.epam.model.User;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends Entity> {
    List<T> query();
    Optional<User> queryForSingleResult();
}
