package org.example.exception;

public class AlreadyBookedException extends RuntimeException {
    public AlreadyBookedException(String msg){
        super(msg);
    }
}
