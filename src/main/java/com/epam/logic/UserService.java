package com.epam.logic;

import com.epam.model.Employee;
import com.epam.repositpry.Repository;
import com.epam.repositpry.UserRepository;
import com.epam.specification.SqlSpecification;
import com.epam.specification.UserSqlSpecification;

import java.util.Optional;

public class UserService {

    public Optional<Employee> login(String login, String password) {
        Repository<Employee> userRepository = new UserRepository();
        SqlSpecification specification = new UserSqlSpecification(login, password);

//        Optional<Employee> employee = userRepository.queryForSingleResult(specification);

        // return userRepository.findUserByPasAndLogin ?
        return userRepository.queryForSingleResult(specification);
    }
}
// return executebystringresult