package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.User;

public interface UserService {
    User findByUserName(String name);
    public void saveUser(User user);
}
