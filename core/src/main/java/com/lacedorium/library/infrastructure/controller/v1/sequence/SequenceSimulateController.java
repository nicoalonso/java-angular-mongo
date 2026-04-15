package com.lacedorium.library.infrastructure.controller.v1.sequence;

import com.lacedorium.library.application.sequence.simulator.SequenceSimulate;
import com.lacedorium.library.domain.sequence.SequenceNumber;
import com.lacedorium.library.presentation.v1.sequence.SequenceReadView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/sequences")
@RequiredArgsConstructor
public class SequenceSimulateController {
    private final SequenceSimulate simulate;

    @RequestMapping("/{type}/simulate")
    public SequenceReadView simulate(@PathVariable String type) {
        SequenceNumber number = simulate.dispatch(type);
        return new SequenceReadView(number);
    }
}
