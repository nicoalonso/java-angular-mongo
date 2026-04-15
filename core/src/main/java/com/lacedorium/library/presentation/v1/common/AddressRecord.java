package com.lacedorium.library.presentation.v1.common;

import com.lacedorium.library.domain.common.Address;
import lombok.NonNull;

public record AddressRecord (
        String street,
        String postalCode,
        String city,
        String province,
        String country
) {
    public static @NonNull AddressRecord make(@NonNull Address address) {
        return new AddressRecord(
                address.getStreet(),
                address.getPostalCode(),
                address.getCity(),
                address.getProvince(),
                address.getCountry()
        );
    }
}
