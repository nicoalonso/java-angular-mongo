package com.lacedorium.library.application.book.list;

import com.lacedorium.library.application.identity.list.EntityList;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.BookRepository;
import com.lacedorium.library.domain.identity.list.Field;
import com.lacedorium.library.domain.identity.list.FilterType;
import com.lacedorium.library.domain.identity.list.ValueKind;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookList extends EntityList<Book> {
    public BookList(BookRepository repository) {
        List<Field> entityMap = List.of(
                new Field("title"),
                new Field("author", "author.name"),
                new Field("editorial", "editorial.name"),
                new Field("isbn", "detail.isbn"),
                new Field("language", "detail.language"),
                new Field("publishedAt", "detail.publishedAt", FilterType.RANGE, ValueKind.DATE),
                new Field("price", "sale.price", FilterType.RANGE, ValueKind.FLOAT),
                new Field("saleable", "sale.saleable", FilterType.MATCH, ValueKind.BOOLEAN)
        );

        super(repository, entityMap);
    }
}
