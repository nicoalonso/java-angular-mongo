package com.lacedorium.library.application.provider.eraser;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class ProviderAssociatedException extends BadRequestException {
    public ProviderAssociatedException() {
        super("Provider cannot be deleted because it is associated with a purchase.");
    }
}
