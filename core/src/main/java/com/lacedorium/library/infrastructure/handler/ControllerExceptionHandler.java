package com.lacedorium.library.infrastructure.handler;

import com.lacedorium.library.domain.identity.exception.BadRequestException;
import com.lacedorium.library.domain.identity.exception.NotFoundException;
import com.lacedorium.library.presentation.v1.identity.ResultError;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Objects;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultError handleNotFoundException(@NonNull NotFoundException ex) {
        return new ResultError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultError handleBadRequestException(@NonNull BadRequestException ex) {
        return new ResultError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    // Spring Boot Exceptions
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultError handleNoResourceFoundException(@NonNull NoResourceFoundException ex) {
        return new ResultError(HttpStatus.NOT_FOUND.value(), ex.getBody().getDetail());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultError handleMethodArgumentNotValidException(@NonNull MethodArgumentNotValidException ex) {
        return new ResultError(
                HttpStatus.BAD_REQUEST.value(),
                Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultError handleInternalServerException(@NonNull Exception ex) {
        return new ResultError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
