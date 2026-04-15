package com.lacedorium.library.domain.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Membership {
    private String number;
    private Boolean active;
    private LocalDateTime endedAt;

    public Membership(String number) {
        this.number = number;
        this.active = true;
        this.endedAt = null;
    }

    public Membership(String number, Boolean active) {
        this.number = number;
        this.active = active;
        this.endedAt = active ? null : LocalDateTime.now();
    }

    public Membership withActive(Boolean active) {
        return new Membership(this.number, active);
    }
}
