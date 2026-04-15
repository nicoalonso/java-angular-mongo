package com.lacedorium.library.application.book.creator;

import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.domain.author.AuthorRepository;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.BookDetail;
import com.lacedorium.library.domain.book.BookRepository;
import com.lacedorium.library.domain.book.BookSale;
import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.domain.editorial.EditorialRepository;
import com.lacedorium.library.domain.user.User;
import com.lacedorium.library.domain.user.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class BookCreate extends BookMakeable {
    private final BookRepository repoBook;
    private final UserRepository repoUser;

    public BookCreate(
            BookRepository repoBook,
            AuthorRepository repoAuthor,
            EditorialRepository repoEditorial,
            UserRepository repoUser
    ) {
        super(repoAuthor, repoEditorial);

        this.repoBook = repoBook;
        this.repoUser = repoUser;
    }

    public @NonNull Book dispatch(BookCreatePayload payload) {
        checkAlreadyExists(payload);

        User user = repoUser.obtainUser();
        Author author = findAuthor(payload.authorId());
        Editorial editorial = findEditorial(payload.editorialId());
        BookDetail detail = makeDetail(payload.detail());
        BookSale sale = makeSale(payload.sale());

        Book book = new Book(
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

    private void checkAlreadyExists(@NonNull BookCreatePayload payload) {
        if (repoBook.obtainByTitle(payload.title()).isPresent()) {
            throw new BookAlreadyExistsException(payload.title());
        }
    }
}
