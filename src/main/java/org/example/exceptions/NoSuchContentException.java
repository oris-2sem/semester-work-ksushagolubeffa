package org.example.exceptions;

import java.util.UUID;

public class NoSuchContentException extends Exception{
    public NoSuchContentException(UUID id){
        super("Content with id " + id.toString() + " does not exist");
    }
}
