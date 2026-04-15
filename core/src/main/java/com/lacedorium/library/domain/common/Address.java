package com.lacedorium.library.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String postalCode;
    private String city;
    private String province;
    private String country;
}
