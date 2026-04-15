package com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MembershipEmbedded {
    private String number;
    private Boolean active;
    private LocalDateTime endedAt;
}
