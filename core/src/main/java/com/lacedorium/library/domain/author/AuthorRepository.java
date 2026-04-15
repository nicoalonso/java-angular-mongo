package com.lacedorium.library.domain.author;

import com.lacedorium.library.domain.identity.IdentityRepository;

import java.util.Optional;

public interface AuthorRepository extends IdentityRepository<Author> {
    Optional<Author> obtainByName(String name);
}
