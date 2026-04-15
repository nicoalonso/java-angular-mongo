package com.lacedorium.library.domain.author;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthorDescriptor {
    private String id;
    private String name;

    public AuthorDescriptor(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
