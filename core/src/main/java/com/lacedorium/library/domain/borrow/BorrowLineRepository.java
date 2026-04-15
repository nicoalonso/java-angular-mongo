package com.lacedorium.library.domain.borrow;

import com.lacedorium.library.domain.identity.IdentityRepository;

import java.util.List;

public interface BorrowLineRepository extends IdentityRepository<BorrowLine> {
    List<BorrowLine> obtainByBorrow(String borrowId);
    List<BorrowLine> obtainByBook(String bookId);
    List<BorrowLine> obtainByBook(String bookId, Integer limit);
    List<BorrowLine> obtainActiveByBook(String bookId);
}
