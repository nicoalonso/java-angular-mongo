package com.lacedorium.library.domain.book;

import com.lacedorium.library.domain.book.exception.InvalidIsbnException;
import com.lacedorium.library.domain.book.exception.InvalidPublishedDateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookDetailTest {
    @Test
    void shouldFailWhenInvalidIsbn() {
        assertThrows(
                InvalidIsbnException.class,
                () -> new BookDetail("First Edition", "", "English", LocalDateTime.now(), 100)
        );
    }

    @Test
    void shouldFailWhenInvalidDate() {
        LocalDateTime publishedAt = LocalDateTime.now().plusDays(2);

        assertThrows(
                InvalidPublishedDateException.class,
                () -> new BookDetail("First Edition", "978-1565921000", "English", publishedAt, 100)
        );
    }

    @Test
    void shouldCreateBookDetail() {
        BookDetail bookDetail = new BookDetail(
                "First Edition",
                "978-1565921000",
                "English",
                LocalDateTime.now(),
                100
        );

        assertEquals("First Edition", bookDetail.getEdition());
        assertEquals("978-1565921000", bookDetail.getIsbn());
        assertEquals("English", bookDetail.getLanguage());
        assertNotNull(bookDetail.getPublishedAt());
        assertEquals(100, bookDetail.getPages());
    }
}