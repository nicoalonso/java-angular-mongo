package com.lacedorium.library.domain.identity;

import com.lacedorium.library.domain.identity.exception.IdEmptyException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Identity {
    protected String id;

    public Identity(String id) {
        isValid(id);
        this.id = id;
    }

    protected void initialize() {
        this.id = generateId();
    }

    public static String generateId() {
        return java.util.UUID.randomUUID().toString();
    }

    private void isValid(String id) {
        if (id == null || id.isEmpty()) {
            throw new IdEmptyException();
        }
    }
}
