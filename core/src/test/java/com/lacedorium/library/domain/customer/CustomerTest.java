package com.lacedorium.library.domain.customer;

import com.lacedorium.library.domain.common.Address;
import com.lacedorium.library.domain.identity.exception.NameEmptyException;
import com.lacedorium.library.fixtures.mothers.AddressMother;
import com.lacedorium.library.fixtures.mothers.ContactInfoMother;
import com.lacedorium.library.fixtures.mothers.MembershipMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private Membership membership;
    private ContactInfo contact;
    private Address address;

    @BeforeEach
    void setUp() {
        membership = MembershipMother.active().build();
        contact = ContactInfoMother.doe().build();
        address = AddressMother.newtown().build();
    }

    @Test
    void shouldFailWhenEmptyName() {
        assertThrows(NameEmptyException.class, () -> new Customer(
                "",
                "Doe",
                membership,
                contact,
                address,
                "123456789A",
                "admin"
        ));
    }

    @Test
    void shouldRunWhenCreate() {
        Customer customer = new Customer(
                "John",
                "Doe",
                membership,
                contact,
                address,
                "123456789A",
                "admin"
        );

        assertNotNull(customer);
        assertEquals("John", customer.getName());
        assertEquals("Doe", customer.getSurname());
        assertEquals(membership, customer.getMembership());
        assertEquals(contact, customer.getContact());
        assertEquals(address, customer.getAddress());
        assertEquals("123456789A", customer.getVatNumber());
    }

    @Test
    void shouldRunWhenModify() {
        Customer customer = new Customer(
                "John",
                "Doe",
                membership,
                contact,
                address,
                "123456789A",
                "admin"
        );

        ContactInfo newContact = ContactInfoMother.doe().with("email", "xxx").build();
        Address newAddress = AddressMother.anytown().with("street", "456 Elm Street").build();

        customer.modify(
                "Will",
                "Smith",
                newContact,
                newAddress,
                "123456789A",
                true,
                "admin"
        );

        assertEquals("Will", customer.getName());
        assertEquals("Smith", customer.getSurname());
        assertTrue(customer.getMembership().getActive());
        assertEquals(newContact, customer.getContact());
        assertEquals(newAddress, customer.getAddress());
        assertEquals("123456789A", customer.getVatNumber());
    }

    @Test
    void shouldRunWhenDeactivate() {
        Customer customer = new Customer(
                "John",
                "Doe",
                membership,
                contact,
                address,
                "123456789A",
                "admin"
        );

        ContactInfo newContact = ContactInfoMother.doe().with("email", "xxx").build();
        Address newAddress = AddressMother.anytown().with("street", "456 Elm Street").build();

        customer.modify(
                "Will",
                "Smith",
                newContact,
                newAddress,
                "123456789A",
                false,
                "admin"
        );

        assertFalse(customer.getMembership().getActive());
        assertNotNull(customer.getMembership().getEndedAt());
    }

    @Test
    void shouldRunWhenGetDescriptor() {
        Customer customer = new Customer(
                "John",
                "Doe",
                membership,
                contact,
                address,
                "123456789A",
                "admin"
        );

        CustomerDescriptor descriptor = customer.getDescriptor();

        assertEquals(customer.getId(), descriptor.getId());
        assertEquals(customer.getName(), descriptor.getName());
        assertEquals(customer.getMembership().getNumber(), descriptor.getNumber());
    }
}