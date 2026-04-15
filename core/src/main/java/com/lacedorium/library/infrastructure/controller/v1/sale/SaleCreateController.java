package com.lacedorium.library.infrastructure.controller.v1.sale;

import com.lacedorium.library.application.sale.creator.SaleCreate;
import com.lacedorium.library.application.sale.creator.SaleCreatePayload;
import com.lacedorium.library.domain.sale.Sale;
import com.lacedorium.library.presentation.v1.sale.SaleCreateView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/sales")
@RequiredArgsConstructor
public class SaleCreateController {
    private final SaleCreate creator;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public SaleCreateView create(@RequestBody SaleCreatePayload payload) {
        Sale sale = creator.dispatch(payload);
        return new SaleCreateView(sale);
    }
}
