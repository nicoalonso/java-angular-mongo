package com.lacedorium.library.domain.identity.list;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FilterRange {
    private Object from;
    private Object to;
    private ValueKind kind;

    public FilterRange(String from, String to) {
        this.from = from;
        this.to = to;
        this.kind = ValueKind.STRING;
    }

    public void parse(ValueKind kind) {
        if (!(from instanceof String) || !(to instanceof String)) {
            return;
        }

        this.kind = kind;
        String fromValue = (String) this.from;
        String toValue = (String) this.to;

        if (kind == ValueKind.DATE) {
            fromValue = fromValue.trim();
            toValue = toValue.trim();
        }

        this.from = kind.parse(fromValue);
        this.to = kind.parse(toValue);

        if (kind == ValueKind.DATE && to != null && ValueKind.isShortDate(toValue)) {
            this.to = ((LocalDateTime) this.to).plusHours(23).plusMinutes(59).plusSeconds(59);
        }
    }

    public void modify(Object from, Object to) {
        if (from != null) {
            this.from = from;
        }
        if (to != null) {
            this.to = to;
        }
    }

    public Boolean hasValue() {
        return hasFrom() || hasTo();
    }

    public Boolean hasFrom() {
        return from != null && (!(from instanceof String) || !((String) from).isBlank());
    }

    public Boolean hasTo() {
        return to != null && (!(to instanceof String) || !((String) to).isBlank());
    }
}
