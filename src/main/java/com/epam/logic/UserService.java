package com.epam.logic;

import com.epam.model.User;
import com.epam.repositpry.Repository;
import com.epam.repositpry.UserRepository;

import java.util.Optional;

public class UserService {

    public Optional<User> login(String login, String password) {
        Repository<User> userRepository = new UserRepository();

        // return userRepository.findUserByPasAndLogin ?
        return null;
    }
}
// return executebystringresult