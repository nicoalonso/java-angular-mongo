package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class AuthorMother extends BaseMother<Author> {
    private static final Map<String, Object> SHAKESPEARE = Map.of(
        "name", "William Shakespeare",
        "realName", "William Shakespeare",
        "genres", "Tragedy, Comedy, History",
        "biography", "William Shakespeare was an English playwright, poet, and actor.",
        "nationality", "English",
        "birthDate", date("1564-04-23"),
        "deathDate", date("1616-04-23"),
        "photoUrl", "https,//example.com/shakespeare.jpg",
        "website", "https,//en.wikipedia.org/wiki/William_Shakespeare",
        "createdBy", "test"
    );

    private static final Map<String, Object> CERVANTES = Map.of(
        "name", "Miguel de Cervantes",
        "realName", "Miguel de Cervantes Saavedra",
        "genres", "Novel, Drama, Poetry",
        "biography", "Miguel de Cervantes was a Spanish writer widely regarded as one of the greatest writers in the Spanish language.",
        "nationality", "Spanish",
        "birthDate", date("1547-09-29"),
        "deathDate", date("1616-04-22"),
        "photoUrl", "https,//example.com/cervantes.jpg",
        "website", "https,//en.wikipedia.org/wiki/Miguel_de_Cervantes",
        "createdBy", "test"
    );

    protected AuthorMother(Map<String, Object> base) {
        super(Author.class, base);
    }

    public static @NonNull AuthorMother shakespeare() {
        return new AuthorMother(SHAKESPEARE);
    }

    public static @NonNull AuthorMother cervantes() {
        return new AuthorMother(CERVANTES);
    }
}
