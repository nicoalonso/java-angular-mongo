package com.lacedorium.library.application.provider.reader;

import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.domain.provider.ProviderRepository;
import com.lacedorium.library.domain.provider.exception.ProviderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderRead {
    private final ProviderRepository repoProvider;

    public Provider dispatch(String providerId) {
        return repoProvider.obtainById(providerId)
                .orElseThrow(() -> new ProviderNotFoundException(providerId));
    }
}
