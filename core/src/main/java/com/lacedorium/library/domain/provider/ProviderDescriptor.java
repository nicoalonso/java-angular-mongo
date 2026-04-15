package com.lacedorium.library.domain.provider;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProviderDescriptor {
    private String id;
    private String name;

    public ProviderDescriptor(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
