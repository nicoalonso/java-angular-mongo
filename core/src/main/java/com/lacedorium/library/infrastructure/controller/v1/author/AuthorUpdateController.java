package com.lacedorium.library.infrastructure.controller.v1.author;

import com.lacedorium.library.application.author.updater.AuthorUpdate;
import com.lacedorium.library.application.author.updater.AuthorUpdatePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/authors")
@RequiredArgsConstructor
public class AuthorUpdateController {
    private final AuthorUpdate updater;

    @PutMapping("/{authorId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void update(@PathVariable String authorId, @RequestBody AuthorUpdatePayload payload) {
        updater.dispatch(authorId, payload);
    }
}
