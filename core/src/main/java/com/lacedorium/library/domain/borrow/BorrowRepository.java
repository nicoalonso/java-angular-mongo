package com.lacedorium.library.domain.borrow;

import com.lacedorium.library.domain.identity.IdentityRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowRepository extends IdentityRepository<Borrow> {
    Optional<Borrow> obtainByNumber(String number);
    List<Borrow> obtainByCustomer(String customerId);
    List<Borrow> obtainByCustomer(String customerId, Integer limit);
    List<Borrow> obtainByOverdue();
}
