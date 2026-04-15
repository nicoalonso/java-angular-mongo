package com.lacedorium.library.infrastructure.controller.v1.provider;

import com.lacedorium.library.application.provider.creator.ProviderCreate;
import com.lacedorium.library.application.provider.creator.ProviderCreatePayload;
import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.presentation.v1.provider.ProviderReadView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/providers")
@RequiredArgsConstructor
public class ProviderCreateController {
    private final ProviderCreate creator;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProviderReadView create(@RequestBody ProviderCreatePayload payload) {
        Provider provider = creator.dispatch(payload);
        return new ProviderReadView(provider);
    }
}
