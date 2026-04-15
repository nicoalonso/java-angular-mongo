package com.lacedorium.library.presentation.v1.author;

import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.presentation.v1.identity.Result;
import org.jspecify.annotations.NonNull;

public record AuthorReadRecord (
        String id,
        String name,
        String realName,
        String genres,
        String biography,
        String nationality,
        String birthDate,
        String deathDate,
        String photoUrl,
        String website,
        String createdBy,
        String createdAt,
        String updatedBy,
        String updatedAt
) {
    public static @NonNull AuthorReadRecord make(@NonNull Author author) {
        return new AuthorReadRecord(
                author.getId(),
                author.getName(),
                author.getRealName(),
                author.getGenres(),
                author.getBiography(),
                author.getNationality(),
                Result.formatShortDate(author.getBirthDate()),
                Result.formatShortDate(author.getDeathDate()),
                author.getPhotoUrl(),
                author.getWebsite(),
                author.getCreatedBy(),
                Result.formatDate(author.getCreatedAt()),
                author.getUpdatedBy(),
                Result.formatDate(author.getUpdatedAt())
        );
    }
}
