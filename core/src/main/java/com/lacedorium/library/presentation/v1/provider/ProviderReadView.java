package com.lacedorium.library.presentation.v1.provider;

import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.presentation.v1.identity.Result;

public class ProviderReadView extends Result<ProviderReadRecord, Provider> {
    public ProviderReadView(Provider provider) {
        super(provider, ProviderReadRecord::make);
    }
}
