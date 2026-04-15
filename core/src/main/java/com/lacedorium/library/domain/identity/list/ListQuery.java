package com.lacedorium.library.domain.identity.list;

import lombok.Getter;
import org.jspecify.annotations.NonNull;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
public class ListQuery {
    private static final int FIRST_PAGE = 1;
    private static final int DEFAULT_PAGE_LIMIT = 10;
    private static final String[] INTERNAL_FIELDS = {"page", "limit", "sort"};

    List<FilterField> filters;
    List<SortField> sort;
    Integer page;
    Integer limit;

    public ListQuery() {
        this.filters = new ArrayList<>();
        this.sort = new ArrayList<>();
        this.page = FIRST_PAGE;
        this.limit = DEFAULT_PAGE_LIMIT;
    }

    public ListQuery(List<FilterField> filters, List<SortField> sort, Integer page, Integer limit) {
        this.filters = filters;
        this.sort = sort;
        this.page = (null != page && page > 0) ? page : FIRST_PAGE;
        this.limit = (null != limit && limit > 0) ? limit : DEFAULT_PAGE_LIMIT;
    }

    public static @NonNull ListQuery parse(ListQueryPayload query) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> payload = mapper.convertValue(query, new TypeReference<Map<String, Object>>() {});

        List<FilterField> filters = parseFilters(payload);
        List<SortField> sort = parseSort(query.sort);

        return new ListQuery(filters, sort, query.getPage(), query.getLimit());
    }

    private static @NonNull List<FilterField> parseFilters(@NonNull Map<String, Object> payload) {
        List<FilterField> filters = new ArrayList<>();
        List<String> internalFields = List.of(INTERNAL_FIELDS);

        for (Map.Entry<String, Object> entry : payload.entrySet()) {
            String valueStr = ValueKind.parseString(entry.getValue());

            if (entry.getKey().isBlank()
                    || internalFields.contains(entry.getKey())
                    || valueStr.isBlank()) {
                continue;
            }

            FilterRangeInterval.CheckResult checkResult = FilterRangeInterval.check(entry.getKey());
            if (checkResult.interval() == FilterRangeInterval.EMPTY) {
                filters.add(new FilterField(checkResult.name(), valueStr));
                continue;
            }

            FilterField filterField = filters.stream()
                    .filter(f -> f.is(checkResult.name()))
                    .findFirst()
                    .orElse(null);

            if (filterField == null) {
                filterField = new FilterField(checkResult.name(), valueStr, FilterType.RANGE);
                filters.add(filterField);
            }
            filterField.range(checkResult.interval(), valueStr);
        }

        return filters;
    }

    private static @NonNull List<SortField> parseSort(String sortValue) {
        List<SortField> sort = new ArrayList<>();

        if (null == sortValue || sortValue.isBlank()) {
            return sort;
        }

        List<String> items = ValueKind.parseList(sortValue);

        for (String item : items) {
            if (item.isBlank()) {
                continue;
            }

            SortField sortField = SortField.fromString(item);
            sort.add(sortField);
        }

        return sort;
    }

    public Boolean hasFilters() {
        return !filters.isEmpty();
    }

    public void addFilter(FilterField filter) {
        filters.add(filter);
    }

    public void removeFilter(String filterName) {
        findFilter(filterName)
                .ifPresent(filter -> this.filters.remove(filter));
    }

    public Optional<FilterField> findFilter(String name) {
        return filters.stream()
                .filter(f -> f.is(name))
                .findFirst();
    }

    public Boolean hasSort() {
        return !sort.isEmpty();
    }

    public void addSort(SortField sort) {
        this.sort.add(sort);
    }

    public void removeSort(String sortName) {
        findSort(sortName)
                .ifPresent(sortField -> this.sort.remove(sortField));
    }

    public Optional<SortField> findSort(String name) {
        return sort.stream()
                .filter(f -> f.is(name))
                .findFirst();
    }

    public Integer offset() {
        return (page - 1) * limit;
    }
}
