package com.lacedorium.library.application.identity.list;

import com.lacedorium.library.application.identity.list.exception.InvalidFilterException;
import com.lacedorium.library.application.identity.list.exception.InvalidSortFieldException;
import com.lacedorium.library.domain.identity.IdentityRepository;
import com.lacedorium.library.domain.identity.list.*;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityList<T> {
    private final IdentityRepository<T> repository;
    private final FieldMap fieldMap;

    public EntityList(@NonNull IdentityRepository<T> repository, @NonNull List<Field> entityMap) {
        this.repository = repository;

        List<Field> defaultMapping = List.of(
                new Field("id"),
                new Field("createdBy"),
                new Field("createdAt", FilterType.RANGE, ValueKind.DATE),
                new Field("updatedBy"),
                new Field("updatedAt", FilterType.RANGE, ValueKind.DATE)
        );

        List<Field> mapping = new ArrayList<>(defaultMapping);
        mapping.addAll(entityMap);

        this.fieldMap = new FieldMap(mapping);
    }

    public ListResult<T> dispatch(ListQuery query) {
        checkQuery(query);
        handleFilters(query);

        return repository.obtainByQuery(query);
    }

    protected void checkQuery(@NonNull ListQuery query) {
        isValidFilterOrFail(query);
        isValidSortOrFail(query);
    }

    protected void isValidFilterOrFail(@NonNull ListQuery query) {
        if (!query.hasFilters()) {
            return;
        }

        for (FilterField filter : query.getFilters()) {
            if (!fieldMap.canFilter(filter)) {
                throw new InvalidFilterException(filter.getAlias());
            }
        }
    }

    private void isValidSortOrFail(@NonNull ListQuery query) {
        if (!query.hasSort()) {
            return;
        }

        for (SortField sort : query.getSort()) {
            if (!fieldMap.canSort(sort)) {
                throw new InvalidSortFieldException(sort.getAlias());
            }
        }
    }

    protected void handleFilters(@NonNull ListQuery query) {
        if (!query.hasSort() && fieldMap.hasField("createdAt")) {
            // add default sort by createdAt if no sort is provided and the field is available
            query.addSort(new SortField("createdAt", SortDirection.DESC));
        }
    }
}
