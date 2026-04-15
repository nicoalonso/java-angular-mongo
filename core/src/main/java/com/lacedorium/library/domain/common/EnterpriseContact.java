package com.lacedorium.library.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseContact {
    private String email;
    private String website;
    private String phone1;
    private String phone2;
}
