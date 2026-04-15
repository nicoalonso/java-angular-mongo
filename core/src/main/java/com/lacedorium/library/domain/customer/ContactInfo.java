package com.lacedorium.library.domain.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContactInfo {
    private String email;
    private String phone1;
    private String phone2;

    public ContactInfo(String email, String phone1, String phone2) {
        this.email = email;
        this.phone1 = phone1;
        this.phone2 = phone2;
    }
}
