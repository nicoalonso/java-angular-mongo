package com.lacedorium.library.domain.identity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Entity extends Identity {
    protected String createdBy;
    protected String updatedBy;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public Entity(String createdBy) {
        super();

        initialize();
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedBy = null;
        this.updatedAt = null;
    }

    public Entity(String id, String createdBy) {
        super(id);

        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedBy = null;
        this.updatedAt = null;
    }

    protected void updated(String updatedBy) {
        this.updatedBy = updatedBy;
        this.updatedAt = LocalDateTime.now();
    }
}
