package com.lacedorium.library.infrastructure.persistence.session.repository;

import com.lacedorium.library.domain.user.User;
import com.lacedorium.library.domain.user.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SessionUserRepository implements UserRepository {
    @Override
    public @NonNull User obtainUser() {
        List<String> groups = List.of("admin");
        return new User("jdoe@gmail.com", "John Doe", groups);
    }
}
