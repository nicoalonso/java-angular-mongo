package com.lacedorium.library.domain.identity.list;

import lombok.Getter;

import java.util.List;

@Getter
public class ListResult<T> {
    private final List<T> items;
    private final Pagination pagination;

    public ListResult(List<T> items) {
        this(items, new Pagination(items.size()));
    }

    public ListResult(List<T> items, Pagination pagination) {
        this.items = items;
        this.pagination = pagination;
    }
}
