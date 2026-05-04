package com.lacedorium.library.infrastructure.handler;

import com.lacedorium.library.domain.identity.exception.BadRequestException;
import com.lacedorium.library.domain.identity.exception.NotFoundException;
import com.lacedorium.library.presentation.v1.identity.ResultError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerExceptionHandlerTest {
    private ControllerExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new ControllerExceptionHandler();
    }

    @Test
    void shouldHandleNotFound() {
        NotFoundException exception = new NotFoundException("Resource not found");

        ResultError result = handler.handleNotFoundException(exception);

        assertNotNull(result);
        assertEquals(404, result.code());
        assertEquals("Resource not found", result.message());
    }

    @Test
    void shouldHandleBadRequest() {
        BadRequestException exception = new BadRequestException("Invalid request");

        ResultError result = handler.handleBadRequestException(exception);

        assertNotNull(result);
        assertEquals(400, result.code());
        assertEquals("Invalid request", result.message());
    }

    @Test
    void shouldHandleNoResourceFound() {
        NoResourceFoundException exception = new NoResourceFoundException(HttpMethod.GET, "uri", "/");

        ResultError result = handler.handleNoResourceFoundException(exception);

        assertNotNull(result);
        assertEquals(404, result.code());
    }

    @Test
    void shouldHandleInternalServer() {
        Exception exception = new Exception("Internal server error");

        ResultError result = handler.handleInternalServerException(exception);

        assertNotNull(result);
        assertEquals(500, result.code());
    }
}