package com.lacedorium.library.application.book.list;

import com.lacedorium.library.domain.identity.list.ListQueryPayload;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookListQuery extends ListQueryPayload {
    String title;
    String author;
    String editorial;
    String isbn;
    String language;
    String fromPublishedDate;
    String toPublishedDate;
    Double fromPrice;
    Double toPrice;
    Boolean saleable;
}
