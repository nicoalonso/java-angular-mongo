package com.lacedorium.library.domain.bus;

import lombok.Getter;
import lombok.NonNull;

@Getter
public abstract class DomainEvent {
    private final String action;
    private final String type;
    private DomainRoute route;

    public DomainEvent(String action, String type) {
        this.action = action;
        this.type = type;
        this.route = DomainRoute.NONE;
    }

    public DomainEvent(String action, String type, @NonNull DomainRoute route) {
        this.action = action;
        this.type = type;
        this.route = route;
    }

    public void changeRoute(@NonNull DomainRoute route) {
        this.route = route;
    }
}
