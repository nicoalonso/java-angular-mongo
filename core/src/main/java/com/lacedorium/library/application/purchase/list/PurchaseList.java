package com.lacedorium.library.application.purchase.list;

import com.lacedorium.library.application.identity.list.EntityList;
import com.lacedorium.library.domain.identity.list.Field;
import com.lacedorium.library.domain.identity.list.FilterType;
import com.lacedorium.library.domain.identity.list.ValueKind;
import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.domain.purchase.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseList extends EntityList<Purchase> {
    public PurchaseList(PurchaseRepository repository) {
        List<Field> entityMap = List.of(
                new Field("provider", "provider.name"),
                new Field("purchaseAt", FilterType.RANGE, ValueKind.DATE),
                new Field("invoiceNumber")
        );

        super(repository, entityMap);
    }
}
