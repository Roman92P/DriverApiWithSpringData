package com.pashkov.driverapi.app.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {

    private final com.pashkov.driverapi.app.model.User user;

    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       com.pashkov.driverapi.app.model.User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public com.pashkov.driverapi.app.model.User getUser() {
        return user;
    }
}
