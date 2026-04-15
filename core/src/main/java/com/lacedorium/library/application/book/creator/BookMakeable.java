package com.lacedorium.library.application.book.creator;

import com.lacedorium.library.application.book.creator.payload.BookDetailPayload;
import com.lacedorium.library.application.book.creator.payload.BookSalePayload;
import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.domain.author.AuthorRepository;
import com.lacedorium.library.domain.author.exception.AuthorNotFoundException;
import com.lacedorium.library.domain.book.BookDetail;
import com.lacedorium.library.domain.book.BookSale;
import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.domain.editorial.EditorialRepository;
import com.lacedorium.library.domain.editorial.exception.EditorialNotFoundException;
import org.jspecify.annotations.NonNull;

public abstract class BookMakeable {
    private final AuthorRepository repoAuthor;
    private final EditorialRepository repoEditorial;

    protected BookMakeable(
            AuthorRepository repoAuthor,
            EditorialRepository repoEditorial
    ) {
        this.repoAuthor = repoAuthor;
        this.repoEditorial = repoEditorial;
    }

    protected Author findAuthor(String authorId) {
        return repoAuthor.obtainById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
    }

    protected Editorial findEditorial(String editorialId) {
        return repoEditorial.obtainById(editorialId)
                .orElseThrow(() -> new EditorialNotFoundException(editorialId));
    }

    protected @NonNull BookDetail makeDetail(@NonNull BookDetailPayload payload) {
        return new BookDetail(
                payload.edition(),
                payload.isbn(),
                payload.language(),
                payload.getPublishedAt(),
                payload.pages()
        );
    }

    protected @NonNull BookSale makeSale(@NonNull BookSalePayload payload) {
        return new BookSale(
                payload.saleable(),
                payload.price(),
                payload.discount()
        );
    }
}
