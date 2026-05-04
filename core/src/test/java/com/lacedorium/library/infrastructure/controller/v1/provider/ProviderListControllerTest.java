package com.lacedorium.library.infrastructure.controller.v1.provider;

import com.lacedorium.library.application.provider.list.ProviderList;
import com.lacedorium.library.application.provider.list.ProviderListQuery;
import com.lacedorium.library.doubles.infrastructure.persistence.ProviderRepositoryStub;
import com.lacedorium.library.presentation.v1.identity.ListView;
import com.lacedorium.library.presentation.v1.provider.ProviderReadRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProviderListControllerTest {
    private ProviderRepositoryStub repository;
    private ProviderListController controller;

    @BeforeEach
    void setUp() {
        repository = new ProviderRepositoryStub();
        ProviderList lister = new ProviderList(repository);
        controller = new ProviderListController(lister);
    }

    @Test
    void shouldRunList() {
        List<?> providers = repository.attachAll();

        ProviderListQuery query = new ProviderListQuery();
        ListView<ProviderReadRecord> result = controller.listProviders(query);

        assertFalse(result.getItems().isEmpty());
        assertEquals(providers.size(), result.getItems().size());
    }
}