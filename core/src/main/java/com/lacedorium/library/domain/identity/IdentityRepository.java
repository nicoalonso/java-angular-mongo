package com.lacedorium.library.domain.identity;

import com.lacedorium.library.domain.identity.list.ListQuery;
import com.lacedorium.library.domain.identity.list.ListResult;

import java.util.Optional;

public interface IdentityRepository<T> {
    Optional<T> obtainById(String id);
    void save(T entity);
    void remove(String id);
    ListResult<T> obtainByQuery(ListQuery query);
}
