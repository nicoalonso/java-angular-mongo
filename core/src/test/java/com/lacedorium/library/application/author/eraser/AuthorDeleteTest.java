package com.lacedorium.library.application.author.eraser;

import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.domain.author.exception.AuthorNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.AuthorRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDeleteTest {
    private AuthorRepositoryStub repoAuthor;
    private BookRepositoryStub repoBook;
    private AuthorDelete deleter;

    @BeforeEach
    void setUp() {
        repoAuthor = new AuthorRepositoryStub();
        repoBook = new BookRepositoryStub(repoAuthor);
        deleter = new AuthorDelete(repoAuthor, repoBook);
    }

    @Test
    void shouldFailWhenAuthorNotFound() {
        assertThrows(
                AuthorNotFoundException.class,
                () -> deleter.dispatch("unknown-id")
        );
    }

    @Test
    void shouldFailWhenBookAssociated() {
        Author author = repoAuthor.put(Ref.AuthorShakespeare);
        repoBook.attach(Ref.BookDonQuixote);

        assertThrows(
                AuthorBookAssociatedException.class,
                () -> deleter.dispatch(author.getId())
        );
    }

    @Test
    void shouldDeleteAuthor() {
        Author author = repoAuthor.put(Ref.AuthorShakespeare);

        deleter.dispatch(author.getId());

        repoAuthor.assertRemoved();
    }
}