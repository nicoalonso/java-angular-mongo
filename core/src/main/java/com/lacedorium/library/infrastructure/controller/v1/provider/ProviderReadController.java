package com.lacedorium.library.infrastructure.controller.v1.provider;

import com.lacedorium.library.application.provider.reader.ProviderRead;
import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.presentation.v1.provider.ProviderReadView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/providers")
@RequiredArgsConstructor
public class ProviderReadController {
    private final ProviderRead reader;

    @GetMapping("/{providerId}")
    public ProviderReadView read(@PathVariable String providerId) {
        Provider provider = reader.dispatch(providerId);
        return new ProviderReadView(provider);
    }
}
