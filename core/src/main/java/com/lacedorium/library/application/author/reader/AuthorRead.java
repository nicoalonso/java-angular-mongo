package com.lacedorium.library.application.author.reader;

import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.domain.author.AuthorRepository;
import com.lacedorium.library.domain.author.exception.AuthorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorRead {
    private final AuthorRepository repoAuthor;

    public Author dispatch(String authorId) {
        return repoAuthor.obtainById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
    }
}
