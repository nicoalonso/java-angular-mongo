package com.lacedorium.library.domain.editorial;

import com.lacedorium.library.domain.identity.IdentityRepository;

import java.util.Optional;

public interface EditorialRepository extends IdentityRepository<Editorial> {
     Optional<Editorial> obtainByName(String name);
}
