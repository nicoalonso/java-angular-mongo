package com.lacedorium.library.application.author.updater;

import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.domain.author.AuthorRepository;
import com.lacedorium.library.domain.author.exception.AuthorNotFoundException;
import com.lacedorium.library.domain.user.User;
import com.lacedorium.library.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorUpdate {
    private final AuthorRepository repoAuthor;
    private final UserRepository repoUser;

    public Author dispatch(String authorId, @NonNull AuthorUpdatePayload payload) {
        Author author = repoAuthor.obtainById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        User user = repoUser.obtainUser();
        author.modify(
                payload.name(),
                payload.realName(),
                payload.genres(),
                payload.biography(),
                payload.nationality(),
                payload.getBirthDate(),
                payload.getDeathDate(),
                payload.photoUrl(),
                payload.website(),
                user.getName()
        );

        repoAuthor.save(author);
        return author;
    }
}
