package com.lacedorium.library.domain.sale;

import com.lacedorium.library.domain.identity.IdentityRepository;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends IdentityRepository<Sale> {
     Optional<Sale> obtainByNumber(String number);
     List<Sale> obtainByCustomer(String customerId);
     List<Sale> obtainByCustomer(String customerId, Integer limit);
}
