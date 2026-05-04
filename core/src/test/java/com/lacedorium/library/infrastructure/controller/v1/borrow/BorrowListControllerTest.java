package com.lacedorium.library.infrastructure.controller.v1.borrow;

import com.lacedorium.library.application.borrow.list.BorrowList;
import com.lacedorium.library.application.borrow.list.BorrowListQuery;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowRepositoryStub;
import com.lacedorium.library.presentation.v1.borrow.BorrowListRecord;
import com.lacedorium.library.presentation.v1.identity.ListView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BorrowListControllerTest {
    private BorrowRepositoryStub repository;
    private BorrowListController controller;

    @BeforeEach
    void setUp() {
        repository = new BorrowRepositoryStub();
        BorrowList lister = new BorrowList(repository);
        controller = new BorrowListController(lister);
    }

    @Test
    void listBorrowsReturnsAllBorrows() {
        List<?> borrows = repository.attachAll();

        BorrowListQuery query = new BorrowListQuery();
        ListView<BorrowListRecord> result = controller.listBorrows(query);

        assertFalse(result.getItems().isEmpty());
        assertEquals(borrows.size(), result.getItems().size());
    }
}