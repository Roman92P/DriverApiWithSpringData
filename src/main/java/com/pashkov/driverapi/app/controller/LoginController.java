package com.pashkov.driverapi.app.controller;

import com.pashkov.driverapi.app.config.LoginCredentials;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @PostMapping(path = "/login")
    public void login(@RequestBody LoginCredentials loginCredentials) {
    }
}
