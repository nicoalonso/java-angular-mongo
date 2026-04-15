package com.lacedorium.library.infrastructure.controller.v1.sale;

import com.lacedorium.library.application.sale.reader.SaleDecorator;
import com.lacedorium.library.application.sale.reader.SaleRead;
import com.lacedorium.library.presentation.v1.sale.SaleReadView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/sales")
@RequiredArgsConstructor
public class SaleReadController {
    private final SaleRead reader;

    @GetMapping("/{saleId}")
    public SaleReadView read(@PathVariable String saleId) {
        SaleDecorator sale = reader.dispatch(saleId);
        return new SaleReadView(sale);
    }
}
