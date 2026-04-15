package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.customer.ContactInfo;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class ContactInfoMother extends BaseMother<ContactInfo> {
    private static final Map<String, Object> DOE_CONTACT_INFO = Map.of(
            "email", "johndoe@gmail.com",
            "phone1", "+1234567890",
            "phone2", "+0987654321"
    );

    private static final Map<String, Object> SMITH_CONTACT_INFO = Map.of(
            "email", "jsmith@gmail.com",
            "phone1", "+1111111111",
            "phone2", "+2222222222"
    );

    protected ContactInfoMother(Map<String, Object> base) {
        super(ContactInfo.class, base);
    }

    public static @NonNull ContactInfoMother doe() {
        return new ContactInfoMother(DOE_CONTACT_INFO);
    }

    public static @NonNull ContactInfoMother smith() {
        return new ContactInfoMother(SMITH_CONTACT_INFO);
    }
}
