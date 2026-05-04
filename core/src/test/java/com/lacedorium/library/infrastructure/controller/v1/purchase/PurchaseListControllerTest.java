package com.lacedorium.library.infrastructure.controller.v1.purchase;

import com.lacedorium.library.application.purchase.list.PurchaseList;
import com.lacedorium.library.application.purchase.list.PurchaseListQuery;
import com.lacedorium.library.doubles.infrastructure.persistence.PurchaseRepositoryStub;
import com.lacedorium.library.presentation.v1.identity.ListView;
import com.lacedorium.library.presentation.v1.purchase.PurchaseListRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseListControllerTest {
    private PurchaseRepositoryStub repository;
    private PurchaseListController controller;

    @BeforeEach
    void setUp() {
        repository = new PurchaseRepositoryStub();
        PurchaseList lister = new PurchaseList(repository);
        controller = new PurchaseListController(lister);
    }

    @Test
    void shouldRunList() {
        List<?> purchases = repository.attachAll();

        PurchaseListQuery query = new PurchaseListQuery();
        ListView<PurchaseListRecord> result = controller.listPurchases(query);

        assertFalse(result.getItems().isEmpty());
        assertEquals(purchases.size(), result.getItems().size());
    }
}