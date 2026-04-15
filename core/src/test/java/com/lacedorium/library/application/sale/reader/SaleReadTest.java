package com.lacedorium.library.application.sale.reader;

import com.lacedorium.library.domain.sale.Sale;
import com.lacedorium.library.domain.sale.exception.SaleNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.SaleLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.SaleRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaleReadTest {
    private SaleRepositoryStub repoSale;
    private SaleLineRepositoryStub repoSaleLine;
    private SaleRead reader;

    @BeforeEach
    void setUp() {
        repoSale = new SaleRepositoryStub();
        repoSaleLine = new SaleLineRepositoryStub(repoSale);
        reader = new SaleRead(repoSale, repoSaleLine);
    }

    @Test
    void shouldFailWhenNotFound() {
        assertThrows(
                SaleNotFoundException.class,
                () -> reader.dispatch("not-found")
        );
    }

    @Test
    void shouldRunWhenRead() {
        Sale sale = repoSale.put(Ref.SaleJohnDoe1);
        repoSaleLine.attach(Ref.SaleLineJohnDoe1Line1);

        SaleDecorator decorator = reader.dispatch(sale.getId());

        assertNotNull(decorator);
        assertEquals(sale.getId(), decorator.sale().getId());
        assertEquals(1, decorator.lines().size());
    }
}