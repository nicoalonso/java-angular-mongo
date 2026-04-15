package com.lacedorium.library.domain.editorial;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EditorialDescriptor {
    private String id;
    private String name;

    public EditorialDescriptor(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
