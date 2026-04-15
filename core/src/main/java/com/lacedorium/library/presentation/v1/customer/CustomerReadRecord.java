package com.lacedorium.library.presentation.v1.customer;

import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.presentation.v1.common.AddressRecord;
import com.lacedorium.library.presentation.v1.identity.Result;
import lombok.NonNull;

public record CustomerReadRecord (
        String id,
        String name,
        String surname,
        MembershipRecord membership,
        ContactInfoRecord contact,
        AddressRecord address,
        String vatNumber,
        String createdBy,
        String createdAt,
        String updatedBy,
        String updatedAt
) {
    public static @NonNull CustomerReadRecord make(@NonNull Customer customer) {
        return new CustomerReadRecord(
                customer.getId(),
                customer.getName(),
                customer.getSurname(),
                MembershipRecord.make(customer.getMembership()),
                ContactInfoRecord.make(customer.getContact()),
                AddressRecord.make(customer.getAddress()),
                customer.getVatNumber(),
                customer.getCreatedBy(),
                Result.formatDate(customer.getCreatedAt()),
                customer.getUpdatedBy(),
                Result.formatDate(customer.getUpdatedAt())
        );
    }
}
