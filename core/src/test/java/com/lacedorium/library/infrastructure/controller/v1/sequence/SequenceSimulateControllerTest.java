package com.lacedorium.library.infrastructure.controller.v1.sequence;

import com.lacedorium.library.application.sequence.simulator.SequenceSimulate;
import com.lacedorium.library.doubles.infrastructure.persistence.SequenceNumberRepositoryStub;
import com.lacedorium.library.presentation.v1.sequence.SequenceReadView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequenceSimulateControllerTest {
    private SequenceSimulateController controller;

    @BeforeEach
    void setUp() {
        SequenceNumberRepositoryStub repository = new SequenceNumberRepositoryStub();
        SequenceSimulate simulator = new SequenceSimulate(repository);
        controller = new SequenceSimulateController(simulator);
    }

    @Test
    void shouldRunSimulate() {
        SequenceReadView result = controller.simulate("sale");

        assertEquals("F-00001", result.getData().number());
    }
}