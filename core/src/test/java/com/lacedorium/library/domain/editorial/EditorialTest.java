package com.lacedorium.library.domain.editorial;

import com.lacedorium.library.domain.common.Address;
import com.lacedorium.library.domain.common.EnterpriseContact;
import com.lacedorium.library.fixtures.mothers.AddressMother;
import com.lacedorium.library.fixtures.mothers.EnterpriseContactMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditorialTest {
    private Address address;
    private EnterpriseContact contact;

    @BeforeEach
    void setUp() {
        address = AddressMother.anytown().build();
        contact = EnterpriseContactMother.anaya().build();
    }

    @Test
    void shouldFailWhenNameIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Editorial("", "", contact, address, "test")
        );
    }

    @Test
    void shouldCreate() {
        Editorial editorial = new Editorial(
                "Anaya",
                "A leading publisher of science fiction and fantasy books.",
                contact,
                address,
                "test"
        );

        assertNotNull(editorial.getId());
        assertEquals("Anaya", editorial.getName());
        assertEquals("A leading publisher of science fiction and fantasy books.", editorial.getComercialName());
        assertEquals(contact, editorial.getContact());
        assertEquals(address, editorial.getAddress());
    }

    @Test
    void shouldModify() {
        Editorial editorial = new Editorial(
                "Anaya",
                "A leading publisher of science fiction and fantasy books.",
                contact,
                address,
                "test"
        );

        EnterpriseContact newContact = EnterpriseContactMother.anaya()
                .with("email", "anaya@example.com")
                .build();
        Address newAddress = AddressMother.anytown()
                .with("street", "456 Elm Street")
                .build();

        editorial.modify("Planet", "Test", newContact, newAddress, "me");

        assertNotNull(editorial.getId());
        assertEquals("Planet", editorial.getName());
        assertEquals("Test", editorial.getComercialName());
        assertEquals("anaya@example.com", editorial.getContact().getEmail());
        assertEquals("456 Elm Street", editorial.getAddress().getStreet());
    }

    @Test
    void shouldRunWhenGetDescriptor() {
        Editorial editorial = new Editorial(
                "Anaya",
                "A leading publisher of science fiction and fantasy books.",
                contact,
                address,
                "test"
        );

        EditorialDescriptor descriptor = editorial.getDescriptor();

        assertEquals(editorial.getId(), descriptor.getId());
        assertEquals("Anaya", descriptor.getName());
    }
}
