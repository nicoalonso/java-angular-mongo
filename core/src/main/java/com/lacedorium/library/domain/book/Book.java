package com.lacedorium.library.domain.book;

import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.domain.author.AuthorDescriptor;
import com.lacedorium.library.domain.book.exception.TitleEmptyException;
import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.domain.editorial.EditorialDescriptor;
import com.lacedorium.library.domain.identity.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;

@Getter
@NoArgsConstructor
public class Book extends Entity {
    private String title;
    private String description;
    private AuthorDescriptor author;
    private EditorialDescriptor editorial;
    private BookDetail detail;
    private BookSale sale;
    private Integer stock;

    public Book(
            String title,
            String description,
            @NonNull Author author,
            @NonNull Editorial editorial,
            BookDetail detail,
            BookSale sale,
            String createdBy
    ) {
        super(createdBy);
        check(title);

        this.title = title;
        this.description = description;
        this.author = author.getDescriptor();
        this.editorial = editorial.getDescriptor();
        this.detail = detail;
        this.sale = sale;
        this.stock = 0;
    }

    public void modify(
            String title,
            String description,
            @NonNull Author author,
            @NonNull Editorial editorial,
            BookDetail detail,
            BookSale sale,
            String updatedBy
    ) {
        check(title);

        this.title = title;
        this.description = description;
        this.author = author.getDescriptor();
        this.editorial = editorial.getDescriptor();
        this.detail = detail;
        this.sale = sale;
        updated(updatedBy);
    }

    private void check(String title) {
        if (title == null || title.isBlank()) {
            throw new TitleEmptyException();
        }
    }

    public void changeStock(Integer stock) {
        if (stock < 0) {
            stock = 0;
        }
        this.stock = stock;
    }

    public BookDescriptor getDescriptor() {
        return new BookDescriptor(id, title, detail.getIsbn());
    }
}
