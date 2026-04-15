package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.customer.Membership;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class MembershipMother extends BaseMother<Membership> {
    private static final Map<String, Object> ACTIVE = Map.of(
        "number", "SN00025"
    );

    protected MembershipMother(Map<String, Object> base) {
        super(Membership.class, base);
    }

    public static @NonNull MembershipMother active() {
        return new MembershipMother(ACTIVE);
    }
}
