package com.lacedorium.library.infrastructure.controller.v1.sale;

import com.lacedorium.library.application.sale.list.SaleList;
import com.lacedorium.library.application.sale.list.SaleListQuery;
import com.lacedorium.library.doubles.infrastructure.persistence.SaleRepositoryStub;
import com.lacedorium.library.presentation.v1.identity.ListView;
import com.lacedorium.library.presentation.v1.sale.SaleListRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaleListControllerTest {
    private SaleRepositoryStub repository;
    private SaleListController controller;

    @BeforeEach
    void setUp() {
        repository = new SaleRepositoryStub();
        SaleList lister = new SaleList(repository);
        controller = new SaleListController(lister);
    }

    @Test
    void shouldRunList() {
        List<?> sales = repository.attachAll();

        SaleListQuery query = new SaleListQuery();
        ListView<SaleListRecord> result = controller.listSales(query);

        assertFalse(result.getItems().isEmpty());
        assertEquals(sales.size(), result.getItems().size());
    }
}