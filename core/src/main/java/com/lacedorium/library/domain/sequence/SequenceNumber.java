package com.lacedorium.library.domain.sequence;

import com.lacedorium.library.domain.identity.Identity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;

@Getter
@NoArgsConstructor
public class SequenceNumber extends Identity {
    private SequenceType type;
    private String prefix;
    private Integer number;

    public SequenceNumber(@NonNull SequenceType type) {
        this.type = type;
        this.prefix = type.getPrefix();
        this.number = 0;
    }

    @Override
    public String toString() {
        return format();
    }

    public String format() {
        return String.format("%s%05d", prefix, number);
    }

    public SequenceNumber next() {
        this.number++;
        return this;
    }
}
