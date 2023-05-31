package org.example.exceptions;

import java.util.UUID;

public class NoSuchOrderException extends Exception{
    public NoSuchOrderException(UUID id){
        super("Order with this id" + id + "does not exist");
    }
}
