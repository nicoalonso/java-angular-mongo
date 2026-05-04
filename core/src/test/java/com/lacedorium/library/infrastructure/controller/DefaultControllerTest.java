package com.lacedorium.library.infrastructure.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultControllerTest {
    private DefaultController controller;

    @BeforeEach
    void setUp() {
        controller = new DefaultController();
    }

    @Test
    void indexShouldReturnHelloWorld() {
        String result = controller.index();
        assertEquals("Hello World!", result);
    }
}