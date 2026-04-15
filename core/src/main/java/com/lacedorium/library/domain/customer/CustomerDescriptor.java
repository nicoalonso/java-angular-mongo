package com.lacedorium.library.domain.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerDescriptor {
    private String id;
    private String name;
    private String surname;
    private String vatNumber;
    private String number;

    public CustomerDescriptor(String id, String name, String surname, String vatNumber, String number) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.vatNumber = vatNumber;
        this.number = number;
    }
}
