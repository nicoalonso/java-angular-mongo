package com.lacedorium.library.application.book.updater;

import com.lacedorium.library.application.book.creator.BookMakeable;
import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.domain.author.AuthorRepository;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.BookDetail;
import com.lacedorium.library.domain.book.BookRepository;
import com.lacedorium.library.domain.book.BookSale;
import com.lacedorium.library.domain.book.exception.BookNotFoundException;
import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.domain.editorial.EditorialRepository;
import com.lacedorium.library.domain.user.User;
import com.lacedorium.library.domain.user.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class BookUpdate extends BookMakeable {
    private final BookRepository repoBook;
    private final UserRepository repoUser;

    public BookUpdate(
            BookRepository repoBook,
            AuthorRepository repoAuthor,
            EditorialRepository repoEditorial,
            UserRepository repoUser
    ) {
        super(repoAuthor, repoEditorial);
        this.repoBook = repoBook;
        this.repoUser = repoUser;
    }

    public Book dispatch(String bookId, @NonNull BookUpdatePayload payload) {
        Book book = findBookOrFail(bookId);

        Author author = findAuthor(payload.authorId());
        Editorial editorial = findEditorial(payload.editorialId());
        BookDetail detail = makeDetail(payload.detail());
        BookSale sale = makeSale(payload.sale());
        User user = repoUser.obtainUser();

        book.modify(
                payload.title(),
                payload.description(),
                author,
                editorial,
                detail,
                sale,
                user.getName()
        );

        repoBook.save(book);
        return book;
    }

    private @NonNull Book findBookOrFail(String bookId) {
        return repoBook.obtainById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }
}
