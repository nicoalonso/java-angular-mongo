package com.lacedorium.library.application.borrow.checkin;

import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.domain.borrow.BorrowLine;
import com.lacedorium.library.domain.borrow.BorrowLineRepository;
import com.lacedorium.library.domain.borrow.BorrowRepository;
import com.lacedorium.library.domain.borrow.exception.BorrowNotFoundException;
import com.lacedorium.library.domain.user.User;
import com.lacedorium.library.domain.user.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowCheckin {
    private final BorrowRepository repoBorrow;
    private final BorrowLineRepository repoBorrowLine;
    private final UserRepository repoUser;

    public Borrow dispatch(String borrowId, @NonNull BorrowCheckinPayload payload) {
        Borrow borrow = repoBorrow.obtainById(borrowId)
                .orElseThrow(() -> new BorrowNotFoundException(borrowId));

        List<BorrowLine> lines = repoBorrowLine.obtainByBorrow(borrowId);

        checkinLines(lines, payload.lines());

        long countReturned = lines.stream()
                .filter(BorrowLine::getReturned)
                .count();

        User user = repoUser.obtainUser();
        borrow.modify(
                (int) countReturned,
                user.getName()
        );
        repoBorrow.save(borrow);

        return borrow;
    }

    private void checkinLines(List<BorrowLine> lines, @NonNull List<BorrowLineCheckinPayload> payloadLines) {
        for (BorrowLineCheckinPayload payloadLine : payloadLines) {
            if (!payloadLine.returned()) {
                continue;
            }

            BorrowLine line = lines.stream()
                    .filter(l -> l.getId().equals(payloadLine.lineId()))
                    .findFirst()
                    .orElse(null);
            if (null == line) {
                continue;
            }

            line.checkIn();
            repoBorrowLine.save(line);
        }
    }
}
