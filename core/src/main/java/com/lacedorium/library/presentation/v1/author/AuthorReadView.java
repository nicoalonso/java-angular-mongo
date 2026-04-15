package com.lacedorium.library.presentation.v1.author;

import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.presentation.v1.identity.Result;

public class AuthorReadView extends Result<AuthorReadRecord, Author> {
    public AuthorReadView(Author author) {
        super(author, AuthorReadRecord::make);
    }
}
