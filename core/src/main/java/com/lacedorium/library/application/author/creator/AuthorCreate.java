package com.lacedorium.library.application.author.creator;

import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.domain.author.AuthorRepository;
import com.lacedorium.library.domain.user.User;
import com.lacedorium.library.domain.user.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorCreate {
    private final AuthorRepository repoAuthor;
    private final UserRepository repoUser;

    public @NonNull Author dispatch(@NonNull AuthorCreatePayload payload) {
        checkAlreadyExists(payload);

        User user = repoUser.obtainUser();
        Author author = new Author(
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

    private void checkAlreadyExists(@NonNull AuthorCreatePayload payload) {
        if (repoAuthor.obtainByName(payload.name()).isPresent()) {
            throw new AuthorAlreadyExistsException(payload.name());
        }
    }
}
