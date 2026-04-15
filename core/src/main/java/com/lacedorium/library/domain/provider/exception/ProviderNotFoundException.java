package com.lacedorium.library.domain.provider.exception;

import com.lacedorium.library.domain.identity.exception.NotFoundException;

public class ProviderNotFoundException extends NotFoundException {
    public ProviderNotFoundException(String providerId) {
        super("Provider with id '" + providerId + "' not found");
    }
}
