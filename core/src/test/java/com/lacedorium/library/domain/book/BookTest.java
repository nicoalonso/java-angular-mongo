package com.lacedorium.library.domain.book;

import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.domain.book.exception.TitleEmptyException;
import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.fixtures.mothers.AuthorMother;
import com.lacedorium.library.fixtures.mothers.BookDetailMother;
import com.lacedorium.library.fixtures.mothers.BookSaleMother;
import com.lacedorium.library.fixtures.mothers.EditorialMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Author author;
    private Editorial editorial;
    private BookDetail detail;
    private BookSale sale;

    @BeforeEach
    void setUp() {
        author = AuthorMother.shakespeare().build();
        editorial = EditorialMother.anaya().build();
        detail = BookDetailMother.valid().build();
        sale = BookSaleMother.valid().build();
    }

    @Test
    void shouldFailWhenTitleEmpty() {
        assertThrows(
                TitleEmptyException.class,
                () -> new Book("", "desc", author, editorial, detail, sale, "test")
        );
    }

    @Test
    void shouldCreate() {
        Book book = new Book(
                "Don Quijote de la Mancha",
                "A thrilling adventure story.",
                author,
                editorial,
                detail,
                sale,
                "test"
        );

        assertEquals("Don Quijote de la Mancha", book.getTitle());
        assertEquals("A thrilling adventure story.", book.getDescription());
        assertEquals(author.getId(), book.getAuthor().getId());
        assertEquals(editorial.getId(), book.getEditorial().getId());
        assertEquals(detail, book.getDetail());
        assertEquals(sale, book.getSale());
        assertEquals("test", book.getCreatedBy());
    }

    @Test
    void shouldRunWhenModify() {
        Book book = new Book(
                "Don Quijote de la Mancha",
                "A thrilling adventure story.",
                author,
                editorial,
                detail,
                sale,
                "test"
        );

        Author newAuthor = AuthorMother.shakespeare().with("name", "William Shakespeare the Great").build();
        Editorial newEditorial = EditorialMother.anaya().with("name", "Anaya Editorial").build();
        BookDetail newDetail = BookDetailMother.valid().with("pages", 1000).build();
        BookSale newSale = BookSaleMother.valid().with("price", 100.0).build();

        book.modify("Don Quijote de la Mancha", "A thrilling adventure story.", newAuthor, newEditorial, newDetail, newSale, "test");

        assertEquals("Don Quijote de la Mancha", book.getTitle());
        assertEquals("A thrilling adventure story.", book.getDescription());
        assertEquals(newAuthor.getName(), book.getAuthor().getName());
        assertEquals(newEditorial.getName(), book.getEditorial().getName());
        assertEquals(newDetail, book.getDetail());
        assertEquals(newSale, book.getSale());
        assertEquals("test", book.getUpdatedBy());
    }

    @Test
    void shouldRunWhenGetDescriptor() {
        Book book = new Book(
                "Don Quijote de la Mancha",
                "A thrilling adventure story.",
                author,
                editorial,
                detail,
                sale,
                "test"
        );

        BookDescriptor descriptor = book.getDescriptor();

        assertEquals(book.getId(), descriptor.getId());
        assertEquals("Don Quijote de la Mancha", descriptor.getTitle());
        assertEquals(detail.getIsbn(), descriptor.getIsbn());
    }

    @Test
    void shouldRunWhenChangeStock() {
        Book book = new Book(
                "Don Quijote de la Mancha",
                "A thrilling adventure story.",
                author,
                editorial,
                detail,
                sale,
                "test"
        );

        book.changeStock(10);

        assertEquals(10, book.getStock());
    }
}