package com.lacedorium.library.domain.user;

import org.jspecify.annotations.NonNull;

public interface UserRepository {
    @NonNull User obtainUser();
}
