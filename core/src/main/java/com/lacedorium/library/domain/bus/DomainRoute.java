package com.lacedorium.library.domain.bus;

public enum DomainRoute {
    NONE,
    LIBRARY;

    @Override
    public String toString() {
        return super.name().toLowerCase();
    }
}
