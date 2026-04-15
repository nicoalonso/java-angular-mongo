package com.lacedorium.library.application.borrow.sanctioner;

import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.domain.borrow.BorrowLine;
import com.lacedorium.library.domain.borrow.BorrowLineRepository;
import com.lacedorium.library.domain.borrow.BorrowRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowPenalty {
    // This value should be retrieved from admin config
    private static final double PENALTY_VALUE = 5.0f;

    private final BorrowRepository repoBorrow;
    private final BorrowLineRepository repoBorrowLine;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public int dispatch() {
        logger.info("Start borrow penalty process");
        int penaltyBorrowCount = 0;

        List<Borrow> borrows = repoBorrow.obtainByOverdue();
        logger.info("Found {} overdue borrows", borrows.size());

        for (Borrow borrow : borrows) {
            if (manageBorrow(borrow)) {
                penaltyBorrowCount++;
            }
        }

        logger.info("Borrow penalty process finished. Total penalized borrows: {}", penaltyBorrowCount);
        return penaltyBorrowCount;
    }

    private boolean manageBorrow(@NonNull Borrow borrow) {
        logger.info("Processing borrow ID: {}", borrow.getId());
        List<BorrowLine> lines = repoBorrowLine.obtainByBorrow(borrow.getId());
        List<BorrowLine> pendingBooks = lines.stream()
                .filter(line -> !line.getReturned())
                .toList();
        logger.info("Found {} pending books for borrow ID: {}", pendingBooks.size(), borrow.getId());

        if (pendingBooks.isEmpty()) {
            logger.info("No pending books for borrow ID: {}, skipping penalty", borrow.getId());
            return false;
        }

        LocalDateTime now = LocalDateTime.now();
        Double amount = calculatePenalty(borrow, now);
        borrow.penalize(amount);
        logger.info("Calculated penalty amount for borrow ID: {} is {}", borrow.getId(), amount);

        for (BorrowLine line : pendingBooks) {
            line.penalize(amount);
            repoBorrowLine.save(line);
        }

        repoBorrow.save(borrow);
        return true;
    }

    private @NonNull Double calculatePenalty(@NonNull Borrow borrow, LocalDateTime now) {
        long overdueDays = ChronoUnit.DAYS.between(borrow.getDueDate(), now);
        overdueDays = Math.max(overdueDays, 0);
        long overdueWeeks = overdueDays / 7;
        logger.info("Borrow ID: {} is overdue by {} days ({} weeks)", borrow.getId(), overdueDays, overdueWeeks);

        return PENALTY_VALUE + overdueWeeks * PENALTY_VALUE;
    }
}
