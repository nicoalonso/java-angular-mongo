package com.lacedorium.library.domain.author;

import com.lacedorium.library.domain.author.exception.InvalidBirthDateException;
import com.lacedorium.library.domain.author.exception.InvalidDeathDateException;
import com.lacedorium.library.domain.identity.exception.NameEmptyException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {
    @Test
    void shouldCreatedWhenEmptyConstructor() {
        Author author = new Author();

        assertNull(author.getId());
    }

    @Test
    void shouldFailWhenNameIsEmpty() {
        assertThrows(NameEmptyException.class, () -> new Author(
                "",
                "William Shakespeare",
                "Tragedy, Comedy, History",
                "William Shakespeare was an English playwright, poet, and actor.",
                "English",
                LocalDateTime.of(1564, 4, 23, 0, 0),
                LocalDateTime.of(1616, 4, 23, 0, 0),
                "https://example.com/shakespeare.jpg",
                "https://en.wikipedia.org/wiki/William_Shakespeare",
                "test"
        ));
    }

    @Test
    void shouldFailWhenBirthDateCannotBeInFuture() {
        LocalDateTime futureBirtDate = LocalDateTime.now().plusDays(1);

        assertThrows(InvalidBirthDateException.class, () -> new Author(
                "William Shakespeare",
                "William Shakespeare",
                "Tragedy, Comedy, History",
                "William Shakespeare was an English playwright, poet, and actor.",
                "English",
                futureBirtDate,
                LocalDateTime.of(1616, 4, 23, 0, 0),
                "https://example.com/shakespeare.jpg",
                "https://en.wikipedia.org/wiki/William_Shakespeare",
                "test"
        ));
    }

    @Test
    void shouldFailWhenDeathDateCannotBeInFuture() {
        LocalDateTime futureDeathDate = LocalDateTime.now().plusDays(1);

        assertThrows(InvalidDeathDateException.class, () -> new Author(
                "William Shakespeare",
                "William Shakespeare",
                "Tragedy, Comedy, History",
                "William Shakespeare was an English playwright, poet, and actor.",
                "English",
                LocalDateTime.of(1564, 4, 23, 0, 0),
                futureDeathDate,
                "https://example.com/shakespeare.jpg",
                "https://en.wikipedia.org/wiki/William_Shakespeare",
                "test"
        ));
    }

    @Test
    void shouldFailWhenDeathDateBeforeBirthDate() {
        assertThrows(InvalidDeathDateException.class, () -> new Author(
                "William Shakespeare",
                "William Shakespeare",
                "Tragedy, Comedy, History",
                "William Shakespeare was an English playwright, poet, and actor.",
                "English",
                LocalDateTime.of(1616, 4, 23, 0, 0),
                LocalDateTime.of(1564, 4, 23, 0, 0),
                "https://example.com/shakespeare.jpg",
                "https://en.wikipedia.org/wiki/William_Shakespeare",
                "test"
        ));
    }

    @Test
    void shouldRunWhenCreated() {
        Author author = new Author(
                "William Shakespeare",
                "William Shakespeare",
                "Tragedy, Comedy, History",
                "William Shakespeare was an English playwright, poet, and actor.",
                "English",
                LocalDateTime.of(1564, 4, 23, 0, 0),
                LocalDateTime.of(1616, 4, 23, 0, 0),
                "https://example.com/shakespeare.jpg",
                "https://en.wikipedia.org/wiki/William_Shakespeare",
                "test"
        );

        assertNotNull(author.getId());
        assertEquals("William Shakespeare", author.getName());
        assertEquals("William Shakespeare", author.getRealName());
        assertEquals("Tragedy, Comedy, History", author.getGenres());
        assertEquals("William Shakespeare was an English playwright, poet, and actor.", author.getBiography());
        assertEquals("English", author.getNationality());
        assertEquals(LocalDateTime.of(1564, 4, 23, 0, 0), author.getBirthDate());
        assertEquals(LocalDateTime.of(1616, 4, 23, 0, 0), author.getDeathDate());
        assertEquals("https://example.com/shakespeare.jpg", author.getPhotoUrl());
        assertEquals("https://en.wikipedia.org/wiki/William_Shakespeare", author.getWebsite());
    }

    @Test
    void shouldRunWhenGetDescriptor() {
        Author author = new Author(
                "William Shakespeare",
                "William Shakespeare",
                "Tragedy, Comedy, History",
                "William Shakespeare was an English playwright, poet, and actor.",
                "English",
                LocalDateTime.of(1564, 4, 23, 0, 0),
                LocalDateTime.of(1616, 4, 23, 0, 0),
                "https://example.com/shakespeare.jpg",
                "https://en.wikipedia.org/wiki/William_Shakespeare",
                "test"
        );
        AuthorDescriptor descriptor = author.getDescriptor();

        assertEquals(author.getId(), descriptor.getId());
        assertEquals(author.getName(), descriptor.getName());
    }

    @Test
    void shouldRunWhenModify() {
        Author author = new Author(
                "William Shakespeare",
                "William Shakespeare",
                "Tragedy, Comedy, History",
                "William Shakespeare was an English playwright, poet, and actor.",
                "English",
                LocalDateTime.of(1564, 4, 23, 0, 0),
                LocalDateTime.of(1616, 4, 23, 0, 0),
                "https://example.com/shakespeare.jpg",
                "https://en.wikipedia.org/wiki/William_Shakespeare",
                "test"
        );

        author.modify(
                "Miguel de Cervantes",
                "Miguel de Cervantes Saavedra",
                "Novel, Drama, Poetry",
                "Miguel de Cervantes was a Spanish writer widely regarded as one of the greatest writers in the Spanish language.",
                "Spanish",
                LocalDateTime.of(1547, 9, 29, 0, 0),
                LocalDateTime.of(1616, 4, 22, 0, 0),
                "https://example.com/cervantes.jpg",
                "https://en.wikipedia.org/wiki/Miguel_de_Cervantes",
                "test"
        );

        assertEquals("Miguel de Cervantes", author.getName());
    }
}
