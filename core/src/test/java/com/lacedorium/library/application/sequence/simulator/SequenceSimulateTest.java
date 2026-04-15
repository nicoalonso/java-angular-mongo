package com.lacedorium.library.application.sequence.simulator;

import com.lacedorium.library.domain.sequence.SequenceNumber;
import com.lacedorium.library.domain.sequence.exception.InvalidSequenceTypeException;
import com.lacedorium.library.doubles.infrastructure.persistence.SequenceNumberRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequenceSimulateTest {
    private SequenceSimulate simulator;

    @BeforeEach
    void setUp() {
        SequenceNumberRepositoryStub repository = new SequenceNumberRepositoryStub();
        simulator = new SequenceSimulate(repository);
    }

    @Test
    void shouldFailWhenInvalidType() {
        assertThrows(
                InvalidSequenceTypeException.class,
                () -> simulator.dispatch("dummy")
        );
    }

    @Test
    void shouldSimulate() {
        SequenceNumber number = simulator.dispatch("sale");

        assertEquals("F-00001", number.format());
    }
}