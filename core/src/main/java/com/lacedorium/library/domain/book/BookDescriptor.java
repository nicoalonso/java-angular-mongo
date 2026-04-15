package com.lacedorium.library.domain.book;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookDescriptor {
    private String id;
    private String title;
    private String isbn;

    public BookDescriptor(String id, String title, String isb) {
        this.id = id;
        this.title = title;
        this.isbn = isb;
    }
}
