package com.lacedorium.library.presentation.v1.customer;

import com.lacedorium.library.domain.customer.Membership;
import com.lacedorium.library.presentation.v1.identity.Result;
import lombok.NonNull;

public record MembershipRecord (
        String number,
        Boolean active,
        String endedAt
) {
    public static @NonNull MembershipRecord make(@NonNull Membership membership) {
        return new MembershipRecord(
                membership.getNumber(),
                membership.getActive(),
                Result.formatShortDate(membership.getEndedAt())
        );
    }
}
