package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.common.EnterpriseContact;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class EnterpriseContactMother extends BaseMother<EnterpriseContact> {
    private static final Map<String, Object> AMAZON = Map.of(
        "email", "info@amazon.com",
        "website", "https://www.amazon.com",
        "phone1", "+1-800-123-4567",
        "phone2", "+1-800-987-6543"
    );

    private static final Map<String, Object> BEST_BUY = Map.of(
        "email", "info@bestbuy.com",
        "website", "https://www.bestbuy.com",
        "phone1", "+1-800-123-4567",
        "phone2", "+1-800-987-6543"
    );

    private static final Map<String, Object> ANAYA = Map.of(
        "email", "info@anaya.com",
        "website", "https://www.anaya.com",
        "phone1", "+34-900-123-456",
        "phone2", "+34-900-987-654"
    );

    protected EnterpriseContactMother(Map<String, Object> base) {
        super(EnterpriseContact.class, base);
    }

    public static @NonNull EnterpriseContactMother amazon() {
        return new EnterpriseContactMother(AMAZON);
    }

    public static @NonNull EnterpriseContactMother bestBuy() {
        return new EnterpriseContactMother(BEST_BUY);
    }

    public static @NonNull EnterpriseContactMother anaya() {
        return new EnterpriseContactMother(ANAYA);
    }
}
