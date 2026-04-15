package com.lacedorium.library.domain.identity.list;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@EqualsAndHashCode(callSuper = true)
@Data
class DummyPayload extends ListQueryPayload {
    String name;
    String realName;
    String fromAge;
    String toAge;
}

class ListQueryTest {
    @Test
    void shouldCreate() {
        ListQuery query = new ListQuery();

        assertNotNull(query);
        assertEquals(0, query.getFilters().size());
        assertEquals(0, query.getSort().size());
        assertEquals(1, query.getPage());
        assertEquals(10, query.getLimit());
        assertEquals(0, query.offset());
        assertFalse(query.hasFilters());
        assertFalse(query.hasSort());
    }

    @Test
    void shouldValidWhenParseWithFilters() {
        DummyPayload payload = new DummyPayload();
        payload.setName("john");
        payload.setRealName("doe");

        ListQuery query = ListQuery.parse(payload);

        assertTrue(query.hasFilters());
        assertEquals(2, query.getFilters().size());

        FilterField first = query.getFilters().getFirst();
        assertEquals("name", first.getName());
        assertEquals("john", first.getValue());

        FilterField found = query.findFilter("realName").orElseThrow();
        assertEquals("realName", found.getName());
        assertEquals("doe", found.getValue());
    }

    @Test
    void shouldNotFoundWhenFilterNotExists() {
        DummyPayload payload = new DummyPayload();
        payload.setName("john");
        payload.setRealName("doe");

        ListQuery query = ListQuery.parse(payload);

        assertFalse(query.findFilter("nonExistent").isPresent());
    }

    @Test
    void shouldRangeFilterWhenDefineRange() {
        DummyPayload payload = new DummyPayload();
        payload.setFromAge("18");
        payload.setToAge("25");

        ListQuery query = ListQuery.parse(payload);

        assertTrue(query.hasFilters());
        assertEquals(1, query.getFilters().size());
        FilterField filter = query.getFilters().getFirst();
        assertEquals("age", filter.getName());
        assertTrue(filter.isRange());
        assertEquals("18", ((FilterRange) filter.getValue()).getFrom());
        assertEquals("25", ((FilterRange) filter.getValue()).getTo());
    }

    @Test
    void shouldRunWhenEmptyFilters() {
        DummyPayload payload = new DummyPayload();
        payload.setName("");
        payload.setRealName(null);

        ListQuery query = ListQuery.parse(payload);

        assertTrue(query.getFilters().isEmpty());
    }

    @Test
    void shouldRunWhenAddFilter() {
        ListQuery query = new ListQuery();

        FilterField filter = new FilterField("name", "john");
        query.addFilter(filter);

        assertEquals(1, query.getFilters().size());
        assertTrue(query.getFilters().contains(filter));
    }

    @Test
    void shouldRunWhenRemoveFilter() {
        DummyPayload payload = new DummyPayload();
        payload.setName("john");
        payload.setRealName("doe");

        ListQuery query = ListQuery.parse(payload);

        query.removeFilter("name");
    }

    @Test
    void shouldFailWhenRemoveNonexistentFilter() {
        DummyPayload payload = new DummyPayload();
        payload.setName("john");
        payload.setRealName("doe");

        ListQuery query = ListQuery.parse(payload);

        query.removeFilter("nonexistent");
    }

    @Test
    void shouldValidWhenParseWithSort() {
        DummyPayload payload = new DummyPayload();
        payload.setSort("name,-realName");

        ListQuery query = ListQuery.parse(payload);

        assertTrue(query.hasSort());
        assertEquals(2, query.getSort().size());
        assertEquals(SortDirection.ASC, query.findSort("name").orElseThrow().getDirection());
        assertEquals(SortDirection.DESC, query.findSort("realName").orElseThrow().getDirection());
    }

    @Test
    void shouldNotFoundWhenSortNotExists() {
        DummyPayload payload = new DummyPayload();
        payload.setSort("name,-realName");

        ListQuery query = ListQuery.parse(payload);

        assertFalse(query.findSort("nonExistent").isPresent());
    }

    @Test
    void shouldRunWhenSpaceBeforeField() {
        DummyPayload payload = new DummyPayload();
        payload.setSort("  name");

        ListQuery query = ListQuery.parse(payload);

        assertTrue(query.findSort("name").isPresent());
    }

    @Test
    void shouldRunWhenAddSortField() {
        ListQuery query = new ListQuery();

        SortField sortField = new SortField("name");
        query.addSort(sortField);

        assertTrue(query.findSort("name").isPresent());
    }

    @Test
    void shouldRunWhenRemoveSortField() {
        ListQuery query = new ListQuery();

        SortField sortField = new SortField("name");
        query.addSort(sortField);

        assertTrue(query.findSort("name").isPresent());

        query.removeSort("name");

        assertFalse(query.findSort("name").isPresent());
    }

    @Test
    void shouldRunWhenRemoveNonexistentSortField() {
        ListQuery query = new ListQuery();

        SortField sortField = new SortField("name");
        query.addSort(sortField);

        assertTrue(query.findSort("name").isPresent());

        query.removeSort("nonexistent");

        assertTrue(query.findSort("name").isPresent());
    }

    @Test
    void shouldRunWhenParseWithPagination() {
        DummyPayload payload = new DummyPayload();
        payload.setPage(2);
        payload.setLimit(20);

        ListQuery query = ListQuery.parse(payload);

        assertEquals(2, query.getPage());
        assertEquals(20, query.getLimit());
        assertEquals(20, query.offset());
    }
}
