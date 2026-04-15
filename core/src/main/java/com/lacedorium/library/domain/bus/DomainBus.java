package com.lacedorium.library.domain.bus;

public interface DomainBus {
    void dispatch(DomainEvent event);
}
