package com.lacedorium.library.domain.author;

import com.lacedorium.library.domain.author.exception.InvalidBirthDateException;
import com.lacedorium.library.domain.author.exception.InvalidDeathDateException;
import com.lacedorium.library.domain.identity.Entity;
import com.lacedorium.library.domain.identity.exception.NameEmptyException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public final class Author extends Entity {
    private String name;
    private String realName;
    private String genres;
    private String biography;
    private String nationality;
    private LocalDateTime birthDate;
    private LocalDateTime deathDate;
    private String photoUrl;
    private String website;

    public Author(
            String name,
            String realName,
            String genres,
            String biography,
            String nationality,
            LocalDateTime birthDate,
            LocalDateTime deathDate,
            String photoUrl,
            String website,
            String createdBy
    ) {
        super(createdBy);

        check(name, birthDate, deathDate);

        this.name = name;
        this.realName = realName;
        this.genres = genres;
        this.biography = biography;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.photoUrl = photoUrl;
        this.website = website;
    }

    public void modify(
            String name,
            String realName,
            String genres,
            String biography,
            String nationality,
            LocalDateTime birthDate,
            LocalDateTime deathDate,
            String photoUrl,
            String website,
            String updatedBy
    ) {
        check(name, birthDate, deathDate);

        this.name = name;
        this.realName = realName;
        this.genres = genres;
        this.biography = biography;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.photoUrl = photoUrl;
        this.website = website;
        updated(updatedBy);
    }

    private void check(String name, LocalDateTime birthDate, LocalDateTime deathDate) {
        if (name == null || name.isBlank()) {
            throw new NameEmptyException();
        }

        LocalDateTime todayMidnight = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);

        if (birthDate == null || birthDate.isAfter(todayMidnight)) {
            throw new InvalidBirthDateException();
        }

        todayMidnight.plusDays(1);
        if (deathDate != null) {
            if (deathDate.isAfter(todayMidnight)) {
                throw new InvalidDeathDateException();
            }
            if (deathDate.isBefore(birthDate)) {
                throw new InvalidDeathDateException("Death date cannot be before birth date.");
            }
        }
    }

    public @NonNull AuthorDescriptor getDescriptor() {
        return new AuthorDescriptor(id, name);
    }
}
