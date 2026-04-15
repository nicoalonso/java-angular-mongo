package com.lacedorium.library.infrastructure.controller.v1.provider;

import com.lacedorium.library.application.provider.eraser.ProviderDelete;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/providers")
@RequiredArgsConstructor
public class ProviderDeleteController {
    private final ProviderDelete eraser;

    @RequestMapping("/{providerId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteProvider(@PathVariable String providerId) {
        eraser.dispatch(providerId);
    }
}
