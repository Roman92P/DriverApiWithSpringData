package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.Advice;
import com.pashkov.driverapi.app.model.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    User findByUserName(String name);

    public void saveUser(User user);

    Optional<User> findUserByid(long id);

    Optional<User> getLikedAdvices(long id);
}
