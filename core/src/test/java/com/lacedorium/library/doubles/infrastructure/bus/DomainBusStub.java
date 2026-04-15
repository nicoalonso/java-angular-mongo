package com.lacedorium.library.doubles.infrastructure.bus;

import com.lacedorium.library.domain.bus.DomainBus;
import com.lacedorium.library.domain.bus.DomainEvent;
import com.lacedorium.library.doubles.Exceptionable;
import lombok.SneakyThrows;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class DomainBusStub extends Exceptionable implements DomainBus  {
    public List<DomainEvent> events;
    public DomainEvent lastEvent;

    public DomainBusStub() {
        super();

        this.events = new ArrayList<>();
        this.lastEvent = null;
    }

    @SneakyThrows
    @Override
    public void dispatch(DomainEvent event) {
        this.throwException();

        this.lastEvent = event;
        this.events.add(event);
    }

    public void assertDispatch(@NonNull Class<? extends DomainEvent> eventClass) {
        events.stream().filter(eventClass::isInstance).findFirst()
                .orElseThrow(() -> new AssertionError("Expected event of type %s was dispatched".formatted(eventClass.getSimpleName())));
    }
}
