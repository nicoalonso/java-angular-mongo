package com.lacedorium.library.application.sale.list;

import com.lacedorium.library.application.identity.list.EntityList;
import com.lacedorium.library.domain.identity.list.Field;
import com.lacedorium.library.domain.identity.list.FilterType;
import com.lacedorium.library.domain.identity.list.ValueKind;
import com.lacedorium.library.domain.sale.Sale;
import com.lacedorium.library.domain.sale.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleList extends EntityList<Sale> {
    public SaleList(SaleRepository repository) {
        List<Field> entityMap = List.of(
                new Field("customer", "customer.name"),
                new Field("date", "invoice.date", FilterType.RANGE, ValueKind.DATE),
                new Field("number")
        );

        super(repository, entityMap);
    }
}
