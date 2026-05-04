package com.lacedorium.library.infrastructure.controller.v1.sale;

import com.lacedorium.library.application.sale.reader.SaleRead;
import com.lacedorium.library.domain.sale.Sale;
import com.lacedorium.library.doubles.infrastructure.persistence.SaleLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.SaleRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.presentation.v1.sale.SaleReadView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaleReadControllerTest {
    private SaleRepositoryStub repoSale;
    private SaleLineRepositoryStub repoSaleLine;
    private SaleReadController controller;

    @BeforeEach
    void setUp() {
        repoSale = new SaleRepositoryStub();
        repoSaleLine = new SaleLineRepositoryStub(repoSale);
        SaleRead reader = new SaleRead(repoSale, repoSaleLine);
        controller = new SaleReadController(reader);
    }

    @Test
    void shouldRead() {
        Sale sale = repoSale.put(Ref.SaleJohnDoe1);
        repoSaleLine.attach(Ref.SaleLineJohnDoe1Line1);

        SaleReadView result = controller.read(sale.getId());

        assertNotNull(result);
        assertEquals(sale.getId(), result.getData().id());
        assertEquals(1, result.getData().lines().size());
    }
}