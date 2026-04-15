package com.lacedorium.library.doubles.infrastructure.persistence;

import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.domain.editorial.EditorialRepository;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.mothers.EditorialMother;
import lombok.SneakyThrows;

import java.util.Optional;

public class EditorialRepositoryStub
        extends EntityRepositoryStub<Editorial>
        implements EditorialRepository {
    @SneakyThrows
    @Override
    public Optional<Editorial> obtainByName(String name) {
        throwException();
        return Optional.ofNullable(read);
    }

    @Override
    protected void makeFixtures() {
        Editorial anaya = EditorialMother.anaya().build();
        addFixture(Ref.EditorialAnaya, anaya);
    }
}
