package com.lacedorium.library.application.borrow.checkin;

import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.domain.borrow.BorrowLine;
import com.lacedorium.library.domain.borrow.exception.BorrowNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BorrowCheckinTest {
    private BorrowRepositoryStub repoBorrow;
    private BorrowLineRepositoryStub repoBorrowLine;
    private BorrowCheckin checker;
    private FixtureBuilder<BorrowCheckinPayload> fixture;

    @BeforeEach
    void setUp() {
        repoBorrow = new BorrowRepositoryStub();
        repoBorrowLine = new BorrowLineRepositoryStub(repoBorrow);
        UserRepositoryStub repoUser = new UserRepositoryStub();
        checker = new BorrowCheckin(repoBorrow, repoBorrowLine, repoUser);

        fixture = new FixtureBuilder<>(BorrowCheckinPayload.class, "borrow-checkin");
    }

    @Test
    void shouldFailWhenNotFound() {
        BorrowCheckinPayload payload = fixture.build();

        assertThrows(
                BorrowNotFoundException.class,
                () -> checker.dispatch("not-found", payload)
        );
    }

    @Test
    void shouldRunWhenCheckin() {
        Borrow borrow = repoBorrow.put(Ref.BorrowJohnDoe);
        BorrowLine line1 = repoBorrowLine.attach(Ref.BorrowLineJohnRomeoAndJuliet);
        BorrowLine line2 = repoBorrowLine.attach(Ref.BorrowLineJohnQuixote);

        Map<String, Object> data = fixture.load("borrow-checkin");
        //noinspection unchecked
        List<HashMap<String, Object>> lines = (List<HashMap<String, Object>>) data.get("lines");
        lines.get(0).put("lineId", line1.getId());
        lines.get(1).put("lineId", line2.getId());

        BorrowCheckinPayload payload = fixture.buildFrom(data);
        checker.dispatch(borrow.getId(), payload);

        assertEquals(1, borrow.getTotalReturnedBooks());
        repoBorrow.assertStored();
        repoBorrowLine.assertStored();
    }
}