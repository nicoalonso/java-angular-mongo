package com.lacedorium.library.doubles.infrastructure.persistence;

import com.lacedorium.library.domain.user.User;
import com.lacedorium.library.domain.user.UserRepository;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class UserRepositoryStub implements UserRepository {
    private final User user;

    public UserRepositoryStub() {
        this.user = new User("jdoe@gmail.com", "John Doe", List.of("admin"));
    }

    @Override
    public @NonNull User obtainUser() {
        return user;
    }
}
