package com.lacedorium.library.domain.provider;

import com.lacedorium.library.domain.identity.IdentityRepository;

import java.util.Optional;

public interface ProviderRepository extends IdentityRepository<Provider> {
     Optional<Provider> obtainByName(String name);
}
