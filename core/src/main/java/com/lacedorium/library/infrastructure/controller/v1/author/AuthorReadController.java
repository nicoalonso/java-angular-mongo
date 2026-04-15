package com.lacedorium.library.infrastructure.controller.v1.author;

import com.lacedorium.library.application.author.reader.AuthorRead;
import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.presentation.v1.author.AuthorReadView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/authors")
@RequiredArgsConstructor
public class AuthorReadController {
    private final AuthorRead reader;

    @GetMapping("/{authorId}")
    public AuthorReadView getAuthor(@PathVariable String authorId) {
        Author author = reader.dispatch(authorId);
        return new AuthorReadView(author);
    }
}
