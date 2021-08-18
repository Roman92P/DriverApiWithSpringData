package com.pashkov.driverapi.app.util;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;

import java.util.NoSuchElementException;

public class UserUtil {

    public static boolean checkIfAuthenticated(Authentication authentication){
        if(authentication==null ||!authentication.isAuthenticated()){
            throw new NoSuchElementException("User credential not found");
        }
        return true;
    }
}
