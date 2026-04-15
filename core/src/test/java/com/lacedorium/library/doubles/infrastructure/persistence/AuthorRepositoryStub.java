package com.lacedorium.library.doubles.infrastructure.persistence;

import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.domain.author.AuthorRepository;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.mothers.AuthorMother;
import lombok.SneakyThrows;

import java.util.Optional;

public class AuthorRepositoryStub
        extends EntityRepositoryStub<Author>
        implements AuthorRepository {
    @SneakyThrows
    @Override
    public Optional<Author> obtainByName(String name) {
        throwException();
        return Optional.ofNullable(read);
    }

    @Override
    protected void makeFixtures() {
        Author shakespeare = AuthorMother.shakespeare().build();
        addFixture(Ref.AuthorShakespeare, shakespeare);

        Author cervantes = AuthorMother.cervantes().build();
        addFixture(Ref.AuthorCervantes, cervantes);
    }
}
