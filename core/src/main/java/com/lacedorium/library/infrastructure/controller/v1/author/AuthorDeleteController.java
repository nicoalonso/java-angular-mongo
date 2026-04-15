package com.lacedorium.library.infrastructure.controller.v1.author;

import com.lacedorium.library.application.author.eraser.AuthorDelete;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/authors")
@RequiredArgsConstructor
public class AuthorDeleteController {
    private final AuthorDelete eraser;

    @DeleteMapping("/{authorId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable String authorId) {
        eraser.dispatch(authorId);
    }
}
