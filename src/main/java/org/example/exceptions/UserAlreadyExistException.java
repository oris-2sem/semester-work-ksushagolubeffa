package org.example.exceptions;

public class UserAlreadyExistException extends Exception{

    public UserAlreadyExistException(String username){
        super("User with username" + username + " already exist");
    }

    public UserAlreadyExistException(String email, String username){
        super("User with email" + email + " already exist");
    }
}