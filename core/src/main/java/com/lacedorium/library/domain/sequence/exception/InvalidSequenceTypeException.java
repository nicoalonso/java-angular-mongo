package com.lacedorium.library.domain.sequence.exception;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class InvalidSequenceTypeException extends BadRequestException {
    public InvalidSequenceTypeException() {
        super("Invalid sequence type");
    }
}
