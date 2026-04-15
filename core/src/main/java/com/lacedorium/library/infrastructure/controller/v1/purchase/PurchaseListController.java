package com.lacedorium.library.infrastructure.controller.v1.purchase;

import com.lacedorium.library.application.purchase.list.PurchaseList;
import com.lacedorium.library.application.purchase.list.PurchaseListQuery;
import com.lacedorium.library.domain.identity.list.ListQuery;
import com.lacedorium.library.domain.identity.list.ListResult;
import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.presentation.v1.identity.ListView;
import com.lacedorium.library.presentation.v1.purchase.PurchaseListRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/purchases")
@RequiredArgsConstructor
public class PurchaseListController {
    private final PurchaseList lister;

    @GetMapping()
    public ListView<PurchaseListRecord> listPurchases(@ModelAttribute PurchaseListQuery queryParams) {
        ListQuery query = ListQuery.parse(queryParams);
        ListResult<Purchase> result = lister.dispatch(query);
        return ListView.of(result, PurchaseListRecord::make);
    }
}
