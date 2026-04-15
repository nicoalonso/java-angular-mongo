package com.lacedorium.library.application.author.eraser;

import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.domain.author.AuthorRepository;
import com.lacedorium.library.domain.author.exception.AuthorNotFoundException;
import com.lacedorium.library.domain.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorDelete {
    private final AuthorRepository repoAuthor;
    private final BookRepository repoBook;

    public void dispatch(String authorId) {
        Author author = repoAuthor.obtainById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        searchBooksRelated(author);

        repoAuthor.remove(author.getId());
    }

    private void searchBooksRelated(@NonNull Author author) {
        List<?> books = repoBook.obtainByAuthor(author.getId(), 1);
        if (!books.isEmpty()) {
            throw new AuthorBookAssociatedException(author.getId());
        }
    }
}
