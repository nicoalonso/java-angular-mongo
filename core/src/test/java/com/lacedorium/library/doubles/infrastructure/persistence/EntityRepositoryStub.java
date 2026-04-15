package com.lacedorium.library.doubles.infrastructure.persistence;

import com.lacedorium.library.domain.identity.IdentityRepository;
import com.lacedorium.library.domain.identity.list.ListQuery;
import com.lacedorium.library.domain.identity.list.ListResult;
import com.lacedorium.library.domain.identity.list.Pagination;
import com.lacedorium.library.doubles.Exceptionable;
import com.lacedorium.library.fixtures.Ref;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public abstract class EntityRepositoryStub<T> extends Exceptionable implements IdentityRepository<T> {
    protected HashMap<Ref, T> repositoryData;
    protected List<T> list;
    protected T read;
    protected T stored;
    protected String removed;

    public EntityRepositoryStub() {
        this.repositoryData = new HashMap<>();
        this.list = new ArrayList<>();
        this.read = null;
        this.stored = null;
        this.removed = null;

        makeFixtures();
    }

    abstract protected void makeFixtures();

    protected void addFixture(Ref ref, T entity) {
        repositoryData.put(ref, entity);
    }

    @SneakyThrows
    @Override
    public Optional<T> obtainById(String id) {
        throwException();
        return Optional.ofNullable(read);
    }

    @SneakyThrows
    @Override
    public void save(T entity) {
        throwException();
        this.stored = entity;
    }

    @SneakyThrows
    @Override
    public void remove(String id) {
        throwException();
        this.removed = id;
    }

    @Override
    public ListResult<T> obtainByQuery(ListQuery query) {
        Pagination pagination = new Pagination(
                list.size(),
                query.getPage(),
                query.getLimit()
        );
        return new ListResult<>(list, pagination);
    }

    public List<T> attachAll() {
        list = List.copyOf(repositoryData.values());
        return list;
    }

    public T attach(Ref ref) {
        T item = get(ref);
        if (item != null) {
            list.add(item);
        }

        return item;
    }

    public void manualAttach(T item) {
        list.add(item);
    }

    public T put(Ref ref) {
        T item = get(ref);
        if (item != null) {
            read = item;
        }

        return item;
    }

    public void manualPut(T item) {
        read = item;
    }

    public void clear() {
        list.clear();
        read = null;
        stored = null;
        removed = null;
        exception = null;
    }

    public T get(Ref ref) {
        return repositoryData.get(ref);
    }

    public T assertStored() {
        if (stored == null) {
            throw new AssertionError("No entity was stored");
        }

        return stored;
    }

    public void assertNotStored() {
        if (stored != null) {
            throw new AssertionError("The entity was stored");
        }
    }

    public void assertRemoved() {
        if (removed == null) {
            throw new AssertionError("No entity was removed");
        }
    }
}
