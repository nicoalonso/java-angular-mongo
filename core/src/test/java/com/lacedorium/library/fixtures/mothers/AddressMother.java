package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.common.Address;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class AddressMother extends BaseMother<Address> {
    private static final Map<String, Object> ANYTOWN = Map.of(
            "street", "123 Main St",
            "postalCode", "12345",
            "city", "Anytown",
            "province", "Alaska",
            "country", "EEUU"
    );

    private static final Map<String, Object> NEWTON = Map.of(
            "street", "456 Elm Street",
            "postalCode", "67890",
            "city", "Newton",
            "province", "Massachusetts",
            "country", "EEUU"
    );

    protected AddressMother(Map<String, Object> base) {
        super(Address.class, base);
    }

    public static @NonNull AddressMother anytown() {
        return new AddressMother(ANYTOWN);
    }

    public static @NonNull AddressMother newtown() {
        return new AddressMother(NEWTON);
    }
}
