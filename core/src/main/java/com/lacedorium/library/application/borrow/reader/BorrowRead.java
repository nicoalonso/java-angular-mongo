package com.lacedorium.library.application.borrow.reader;

import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.domain.borrow.BorrowLine;
import com.lacedorium.library.domain.borrow.BorrowLineRepository;
import com.lacedorium.library.domain.borrow.BorrowRepository;
import com.lacedorium.library.domain.borrow.exception.BorrowNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowRead {
    private final BorrowRepository repoBorrow;
    private final BorrowLineRepository repoBorrowLine;

    public BorrowDecorator dispatch(String borrowId) {
        Borrow borrow = repoBorrow.obtainById(borrowId)
                .orElseThrow(() -> new BorrowNotFoundException(borrowId));

        List<BorrowLine> lines = repoBorrowLine.obtainByBorrow(borrowId);

        return new BorrowDecorator(borrow, lines);
    }
}
