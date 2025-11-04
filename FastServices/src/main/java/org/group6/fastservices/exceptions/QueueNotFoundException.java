package org.group6.fastservices.exceptions;

public class QueueNotFoundException extends RuntimeException {
    public QueueNotFoundException(String message) {
        super(message);
    }
}
