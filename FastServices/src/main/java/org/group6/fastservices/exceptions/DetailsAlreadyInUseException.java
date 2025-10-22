package org.group6.fastservices.exceptions;

public class DetailsAlreadyInUseException extends RuntimeException {
    public DetailsAlreadyInUseException(String message) {
        super(message);
    }
}
