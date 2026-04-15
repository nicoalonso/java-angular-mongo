package com.lacedorium.library.domain.provider;

import com.lacedorium.library.domain.common.Address;
import com.lacedorium.library.domain.common.EnterpriseContact;
import com.lacedorium.library.domain.identity.exception.NameEmptyException;
import com.lacedorium.library.fixtures.mothers.AddressMother;
import com.lacedorium.library.fixtures.mothers.EnterpriseContactMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProviderTest {
    private EnterpriseContact contact;
    private Address address;

    @BeforeEach
    void setUp() {
        contact = EnterpriseContactMother.amazon().build();
        address = AddressMother.newtown().build();
    }

    @Test
    void shouldFailWhenNameIsEmpty() {
        assertThrows(NameEmptyException.class, () -> new Provider(
                "",
                "Amazon",
                contact,
                address,
                "B123456789",
                "testUser"
        ));
    }

    @Test
    void shouldCreate() {
        Provider provider = new Provider(
                "Amazon SL",
                "Amazon",
                contact,
                address,
                "B123456789",
                "testUser"
        );

        assertNotNull(provider);
        assertEquals("Amazon SL", provider.getName());
        assertEquals("Amazon", provider.getComercialName());
        assertEquals(contact, provider.getContact());
        assertEquals(address, provider.getAddress());
        assertEquals("B123456789", provider.getVatNumber());
    }

    @Test
    void shouldRunWhenModify() {
        Provider provider = new Provider(
                "Amazon SL",
                "Amazon",
                contact,
                address,
                "B123456789",
                "testUser"
        );

        EnterpriseContact newContact = EnterpriseContactMother.amazon().with("email", "xxxx").build();
        Address newAddress = AddressMother.newtown().with("street", "456 Elm Street").build();

        provider.modify(
                "Best Buy",
                "BestBuy",
                newContact,
                newAddress,
                "B987865644",
                "testUser"
        );

        assertEquals("Best Buy", provider.getName());
        assertEquals("BestBuy", provider.getComercialName());
        assertEquals(newContact, provider.getContact());
        assertEquals(newAddress, provider.getAddress());
        assertEquals("B987865644", provider.getVatNumber());
    }

    @Test
    void shouldRunWhenGetDescriptor() {
        Provider provider = new Provider(
                "Amazon SL",
                "Amazon",
                contact,
                address,
                "B123456789",
                "testUser"
        );

        ProviderDescriptor descriptor = provider.getDescriptor();

        assertEquals(provider.getId(), descriptor.getId());
        assertEquals("Amazon SL", descriptor.getName());
    }
}