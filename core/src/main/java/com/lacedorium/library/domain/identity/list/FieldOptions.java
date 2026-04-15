package com.lacedorium.library.domain.identity.list;

import lombok.NonNull;

public class FieldOptions {
    private Boolean canSelect;
    private Boolean canFilter;
    private Boolean canSort;

    public FieldOptions() {
        this.canSelect = true;
        this.canFilter = true;
        this.canSort = true;
    }

    public void add(@NonNull FieldOption option) {
        switch (option) {
            case NO_SELECT:
                canSelect = false;
                break;
            case NO_FILTER:
                canFilter = false;
                break;
            case NO_SORT:
                canSort = false;
                break;
        }
    }

    public Boolean canSelect() {
        return canSelect;
    }

    public Boolean canFilter() {
        return canFilter;
    }

    public Boolean canSort() {
        return canSort;
    }
}
