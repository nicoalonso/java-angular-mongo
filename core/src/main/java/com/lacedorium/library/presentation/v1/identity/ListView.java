package com.lacedorium.library.presentation.v1.identity;

import com.lacedorium.library.domain.identity.list.ListResult;
import com.lacedorium.library.domain.identity.list.Pagination;
import lombok.Getter;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.Function;

@Getter
public class ListView<T> {
    private final List<T> items;
    private final Pagination pagination;

    public ListView(List<T> items, Pagination pagination) {
        this.items = items;
        this.pagination = pagination;
    }

    public static <T, U> @NonNull ListView<T> of(@NonNull ListResult<U> result, Function<U, T> maker) {
        List<T> mappedItems = result.getItems().stream().map(maker).toList();
        return new ListView<>(mappedItems, result.getPagination());
    }
}
