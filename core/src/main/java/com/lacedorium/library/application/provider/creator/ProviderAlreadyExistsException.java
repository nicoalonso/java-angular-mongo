package com.lacedorium.library.application.provider.creator;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class ProviderAlreadyExistsException extends BadRequestException {
    public ProviderAlreadyExistsException(String name) {
        super("Provider with name '" + name + "' already exists");
    }
}
