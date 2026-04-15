package com.lacedorium.library.domain.borrow;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.BookDescriptor;
import com.lacedorium.library.domain.identity.Identity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BorrowLine extends Identity {
    private String borrow;
    private BookDescriptor book;
    private Boolean returned;
    private LocalDateTime returnedDate;
    private Boolean penalty;
    private Double penaltyAmount;

    public BorrowLine(
            @NonNull Borrow borrow,
            @NonNull Book book
    ) {
        super();
        initialize();

        this.borrow = borrow.getId();
        this.book = book.getDescriptor();
        this.returned = false;
        this.returnedDate = null;
        this.penalty = false;
        this.penaltyAmount = 0.0;
    }

    public void checkIn() {
        this.returned = true;
        this.returnedDate = LocalDateTime.now();
    }

    public void penalize(@NonNull Double amount) {
        this.penalty = true;
        this.penaltyAmount = amount;
    }
}
