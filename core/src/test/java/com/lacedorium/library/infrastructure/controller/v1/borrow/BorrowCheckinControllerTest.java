package com.lacedorium.library.infrastructure.controller.v1.borrow;

import com.lacedorium.library.application.borrow.checkin.BorrowCheckin;
import com.lacedorium.library.application.borrow.checkin.BorrowCheckinPayload;
import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BorrowCheckinControllerTest {
    private BorrowRepositoryStub repoBorrow;
    private BorrowLineRepositoryStub repoBorrowLine;
    private BorrowCheckinController controller;
    private FixtureBuilder<BorrowCheckinPayload> fixture;

    @BeforeEach
    void setUp() {
        repoBorrow = new BorrowRepositoryStub();
        repoBorrowLine = new BorrowLineRepositoryStub(repoBorrow);
        UserRepositoryStub repoUser = new UserRepositoryStub();
        BorrowCheckin checker = new BorrowCheckin(repoBorrow, repoBorrowLine, repoUser);
        controller = new BorrowCheckinController(checker);

        fixture = new FixtureBuilder<>(BorrowCheckinPayload.class, "borrow-checkin");
    }

    @Test
    void shouldChecker() {
        Borrow borrow = repoBorrow.put(Ref.BorrowJohnDoe);
        repoBorrowLine.attach(Ref.BorrowLineJohnRomeoAndJuliet);
        BorrowCheckinPayload payload = fixture.build();

        controller.checkin(borrow.getId(), payload);

        repoBorrow.assertStored();
    }
}