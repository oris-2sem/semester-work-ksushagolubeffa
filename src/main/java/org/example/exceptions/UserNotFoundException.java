package org.example.exceptions;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(@NotNull UUID uuid){
        super("User with id " + uuid + " not found");
    }

    public UserNotFoundException(String email){
        super("User with email" + email + " not found");
    }
}
