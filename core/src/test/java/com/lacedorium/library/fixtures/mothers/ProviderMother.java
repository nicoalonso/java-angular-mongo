package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class ProviderMother extends BaseMother<Provider> {
    private static final Map<String, Object> AMAZON = Map.of(
            "name", "Amazon",
            "comercialName", "Amazon, Inc.",
            "contact", lazy(EnterpriseContactMother::amazon),
            "address", lazy(AddressMother::anytown),
            "vatNumber", "B36565656",
            "createdBy", "test"
    );

    private static final Map<String, Object> BEST_BUY = Map.of(
            "name", "Best Buy",
            "comercialName", "Best Buy Co., Inc.",
            "contact", lazy(EnterpriseContactMother::bestBuy),
            "address", lazy(AddressMother::newtown),
            "vatNumber", "B36565656",
            "createdBy", "test"
    );

    protected ProviderMother(Map<String, Object> base) {
        super(Provider.class, base);
    }

    public static @NonNull ProviderMother amazon() {
        return new ProviderMother(AMAZON);
    }

    public static @NonNull ProviderMother bestBuy() {
        return new ProviderMother(BEST_BUY);
    }
}
