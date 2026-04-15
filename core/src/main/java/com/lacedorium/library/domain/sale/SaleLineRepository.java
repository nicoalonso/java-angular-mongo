package com.lacedorium.library.domain.sale;

import com.lacedorium.library.domain.identity.IdentityRepository;

import java.util.List;

public interface SaleLineRepository extends IdentityRepository<SaleLine> {
    List<SaleLine> obtainBySale(String saleId);
    List<SaleLine> obtainByBook(String bookId);
    List<SaleLine> obtainByBook(String bookId, Integer limit);
}
