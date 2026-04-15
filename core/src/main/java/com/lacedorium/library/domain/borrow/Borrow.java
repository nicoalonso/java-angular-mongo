package com.lacedorium.library.domain.borrow;

import com.lacedorium.library.domain.borrow.exception.InvalidBorrowNumberException;
import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.domain.customer.CustomerDescriptor;
import com.lacedorium.library.domain.identity.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Borrow extends Entity {
    private static final int INTERVAL_DUE_DATE = 14;

    private CustomerDescriptor customer;
    private String number;
    private LocalDateTime borrowDate;
    private Integer totalBooks;
    private LocalDateTime dueDate;
    private Integer totalReturnedBooks;
    private Boolean returned;
    private LocalDateTime returnedDate;
    private Boolean penalty;
    private Double penaltyAmount;

    public Borrow(
            @NonNull Customer customer,
            String number,
            Integer totalBooks,
            String createdBy
    ) {
        super(createdBy);

        check(number);

        this.customer = customer.getDescriptor();
        this.number = number;
        this.borrowDate = LocalDateTime.now();
        this.totalBooks = totalBooks;
        this.dueDate = borrowDate.plusDays(INTERVAL_DUE_DATE);
        this.totalReturnedBooks = 0;
        this.returnedDate = null;
        this.returned = false;
        this.penalty = false;
        this.penaltyAmount = 0.0;
    }

    private void check(String number) {
        if (number == null || number.isEmpty()) {
            throw new InvalidBorrowNumberException();
        }
    }

    public void modify(Integer returnedBooks, String updatedBy) {
        totalReturnedBooks = returnedBooks;
        returned = totalReturnedBooks >= totalBooks;
        returnedDate = returned ? LocalDateTime.now() : null;
        updated(updatedBy);
    }

    public void penalize(Double amount) {
        penalty = true;
        penaltyAmount = amount;
    }
}
