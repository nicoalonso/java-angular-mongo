package com.lacedorium.library.infrastructure.controller.v1.author;

import com.lacedorium.library.application.author.creator.AuthorCreate;
import com.lacedorium.library.application.author.creator.AuthorCreatePayload;
import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.presentation.v1.author.AuthorReadView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/authors")
@RequiredArgsConstructor
public class AuthorCreateController {
    private final AuthorCreate creator;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public AuthorReadView create(@RequestBody AuthorCreatePayload payload) {
        Author author = creator.dispatch(payload);
        return new AuthorReadView(author);
    }
}
