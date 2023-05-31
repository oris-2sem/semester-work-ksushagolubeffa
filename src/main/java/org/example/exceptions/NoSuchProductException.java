package org.example.exceptions;

import java.util.UUID;

public class NoSuchProductException extends Exception{
    public NoSuchProductException(UUID id){
        super("Product with id " + id+ " does not exist");
    }
}
