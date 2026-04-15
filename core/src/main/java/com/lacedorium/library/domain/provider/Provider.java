package com.lacedorium.library.domain.provider;

import com.lacedorium.library.domain.common.Address;
import com.lacedorium.library.domain.common.EnterpriseContact;
import com.lacedorium.library.domain.identity.Entity;
import com.lacedorium.library.domain.identity.exception.NameEmptyException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class Provider extends Entity {
    private String name;
    private String comercialName;
    private EnterpriseContact contact;
    private Address address;
    private String vatNumber;

    public Provider(
            String name,
            String comercialName,
            EnterpriseContact contact,
            Address address,
            String vatNumber,
            String createdBy
    ) {
        super(createdBy);

        check(name);

        this.name = name;
        this.comercialName = comercialName;
        this.contact = contact;
        this.address = address;
        this.vatNumber = vatNumber;
    }

    public void modify(
            String name,
            String comercialName,
            EnterpriseContact contact,
            Address address,
            String vatNumber,
            String updatedBy
    ) {
        check(name);

        this.name = name;
        this.comercialName = comercialName;
        this.contact = contact;
        this.address = address;
        this.vatNumber = vatNumber;
        updated(updatedBy);
    }

    private void check(String name) {
        if (name == null || name.isBlank()) {
            throw new NameEmptyException();
        }
    }

    public @NonNull ProviderDescriptor getDescriptor() {
        return new ProviderDescriptor(id, name);
    }
}
