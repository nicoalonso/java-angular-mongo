package com.lacedorium.library.application.borrow.list;

import com.lacedorium.library.domain.identity.list.ListQueryPayload;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BorrowListQuery extends ListQueryPayload {
    String customerId;
    String customer;
    String customerNumber;
    String number;
    String fromBorrowDate;
    String toBorrowDate;
    String fromDueDate;
    String toDueDate;
    Integer totalBooks;
    Boolean returned;
    Boolean penalty;
}
