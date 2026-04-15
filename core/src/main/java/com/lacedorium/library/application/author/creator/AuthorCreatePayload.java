package com.lacedorium.library.application.author.creator;

import com.lacedorium.library.domain.author.exception.InvalidBirthDateException;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AuthorCreatePayload(
        String name,
        String realName,
        String genres,
        String biography,
        String nationality,
        LocalDate birthDate,
        LocalDate deathDate,
        String photoUrl,
        String website
) {
    public @NonNull LocalDateTime getBirthDate() {
        if (birthDate == null) {
            throw new InvalidBirthDateException("Birth date is required");
        }

        return birthDate.atStartOfDay();
    }

    public @Nullable LocalDateTime getDeathDate() {
        if (deathDate == null) {
            return null;
        }

        return deathDate.atStartOfDay();
    }
}
