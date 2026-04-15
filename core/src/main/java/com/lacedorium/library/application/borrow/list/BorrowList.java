package com.lacedorium.library.application.borrow.list;

import com.lacedorium.library.application.identity.list.EntityList;
import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.domain.borrow.BorrowRepository;
import com.lacedorium.library.domain.identity.list.Field;
import com.lacedorium.library.domain.identity.list.FieldOption;
import com.lacedorium.library.domain.identity.list.FilterType;
import com.lacedorium.library.domain.identity.list.ValueKind;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowList extends EntityList<Borrow> {
    public BorrowList(BorrowRepository repository) {
        List<Field> entityMap = List.of(
                new Field("customerId", "customer.id"),
                new Field("customer", "customer.name"),
                new Field("customerNumber", "customer.number"),
                new Field("number"),
                new Field("borrowDate", FilterType.RANGE, ValueKind.DATE),
                new Field("dueDate", FilterType.RANGE, ValueKind.DATE),
                new Field("totalBooks", FieldOption.NO_FILTER),
                new Field("returned", FilterType.MATCH, ValueKind.BOOLEAN),
                new Field("penalty", FilterType.MATCH, ValueKind.BOOLEAN)
        );

        super(repository, entityMap);
    }
}
