package com.lacedorium.library.infrastructure.controller.v1.provider;

import com.lacedorium.library.application.provider.updater.ProviderUpdate;
import com.lacedorium.library.application.provider.updater.ProviderUpdatePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/providers")
@RequiredArgsConstructor
public class ProviderUpdateController {
    private final ProviderUpdate updater;

    @PutMapping("/{providerId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void update(@PathVariable String providerId, @RequestBody ProviderUpdatePayload payload) {
        updater.dispatch(providerId, payload);
    }
}
