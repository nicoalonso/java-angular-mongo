package com.lacedorium.library.domain.editorial;

import com.lacedorium.library.domain.common.Address;
import com.lacedorium.library.domain.common.EnterpriseContact;
import com.lacedorium.library.domain.identity.Entity;
import com.lacedorium.library.domain.identity.exception.NameEmptyException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class Editorial extends Entity {
    private String name;
    private String comercialName;
    private EnterpriseContact contact;
    private Address address;

    public Editorial(
            String name,
            String comercialName,
            EnterpriseContact contact,
            Address address,
            String createdBy
    ) {
        super(createdBy);

        check(name);

        this.name = name;
        this.comercialName = comercialName;
        this.contact = contact;
        this.address = address;
    }

    public void modify(
            String name,
            String comercialName,
            EnterpriseContact contact,
            Address address,
            String updatedBy
    ) {
        check(name);

        this.name = name;
        this.comercialName = comercialName;
        this.contact = contact;
        this.address = address;
        updated(updatedBy);
    }

    private void check(String name) {
        if (name == null || name.isBlank()) {
            throw new NameEmptyException();
        }
    }

    public @NonNull EditorialDescriptor getDescriptor() {
        return new EditorialDescriptor(id, name);
    }
}
