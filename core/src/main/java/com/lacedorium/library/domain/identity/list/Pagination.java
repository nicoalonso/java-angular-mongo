package com.lacedorium.library.domain.identity.list;

import lombok.Getter;

@Getter
public class Pagination {
    private final long total;
    private final long page;
    private final long rowsPerPage;

    public Pagination() {
        this(0, 1, 10);
    }

    public Pagination(long total) {
        this(total, 1, 10);
    }

    public Pagination(long total, long page, long rowsPerPage) {
        if (total < 0 || page < 0 || rowsPerPage < 0) {
            throw new IllegalArgumentException("Pagination values cannot be negative");
        }

        this.total = total;
        this.page = page;
        this.rowsPerPage = rowsPerPage;
    }

    public long getTotalPages() {
        return (long) Math.ceil((double) total / rowsPerPage);
    }
}
