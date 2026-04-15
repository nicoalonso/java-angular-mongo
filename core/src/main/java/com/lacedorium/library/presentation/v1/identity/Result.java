package com.lacedorium.library.presentation.v1.identity;

import lombok.Getter;
import org.jspecify.annotations.NonNull;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@Getter
public class Result<T, U> {
    private final T data;

    public Result(U entity, @NonNull Function<U, T> maker) {
        this.data = maker.apply(entity);
    }

    public static String formatShortDate(LocalDateTime date) {
        if (date == null) {
            return null;
        }

        return date.toLocalDate().toString();
    }

    public static String formatDate(LocalDateTime date) {
        if (date == null) {
            return null;
        }

        ZonedDateTime zdt = date.atZone(ZoneId.of("Europe/Madrid"));
        return zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));
    }
}
