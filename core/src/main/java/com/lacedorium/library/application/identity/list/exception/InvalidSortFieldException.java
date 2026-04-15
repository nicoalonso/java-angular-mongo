package com.lacedorium.library.application.identity.list.exception;

public class InvalidSortFieldException extends RuntimeException {
    public InvalidSortFieldException(String name) {
        super("Invalid sort field: " + name);
    }
}
