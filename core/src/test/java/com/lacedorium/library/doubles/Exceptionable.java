package com.lacedorium.library.doubles;

public class Exceptionable {
    protected Exception exception;

    public Exceptionable() {
        this.exception = null;
    }

    public void error(String message) {
        this.error(new Exception(message));
    }

    public void error(Exception exception) {
        this.exception = exception;
    }

    protected void throwException() throws Exception {
        if (exception != null) {
            throw exception;
        }
    }
}
