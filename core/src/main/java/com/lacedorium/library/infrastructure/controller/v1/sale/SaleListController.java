package com.lacedorium.library.infrastructure.controller.v1.sale;

import com.lacedorium.library.application.sale.list.SaleList;
import com.lacedorium.library.application.sale.list.SaleListQuery;
import com.lacedorium.library.domain.identity.list.ListQuery;
import com.lacedorium.library.domain.identity.list.ListResult;
import com.lacedorium.library.domain.sale.Sale;
import com.lacedorium.library.presentation.v1.identity.ListView;
import com.lacedorium.library.presentation.v1.sale.SaleListRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/sales")
@RequiredArgsConstructor
public class SaleListController {
    private final SaleList lister;

    @GetMapping()
    public ListView<SaleListRecord> listSales(@ModelAttribute SaleListQuery queryParams) {
        ListQuery query = ListQuery.parse(queryParams);
        ListResult<Sale> result = lister.dispatch(query);
        return ListView.of(result, SaleListRecord::make);
    }
}
