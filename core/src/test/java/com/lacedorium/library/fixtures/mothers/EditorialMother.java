package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class EditorialMother extends BaseMother<Editorial> {
    private static final Map<String, Object> ANAYA = Map.of(
            "name", "Anaya",
            "comercialName", "Anaya Editorial",
            "contact", lazy(EnterpriseContactMother::anaya),
            "address", lazy(AddressMother::anytown),
            "createdBy", "system"
    );

    protected EditorialMother(Map<String, Object> base) {
        super(Editorial.class, base);
    }

    public static @NonNull EditorialMother anaya() {
        return new EditorialMother(ANAYA);
    }
}
