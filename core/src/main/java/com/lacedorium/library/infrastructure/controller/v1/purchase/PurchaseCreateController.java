package com.lacedorium.library.infrastructure.controller.v1.purchase;

import com.lacedorium.library.application.purchase.creator.PurchaseCreate;
import com.lacedorium.library.application.purchase.creator.PurchaseCreatePayload;
import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.presentation.v1.purchase.PurchaseCreateView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/purchases")
@RequiredArgsConstructor
public class PurchaseCreateController {
    private final PurchaseCreate creator;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public PurchaseCreateView create(@RequestBody PurchaseCreatePayload payload) {
        Purchase purchase = creator.dispatch(payload);
        return new PurchaseCreateView(purchase);
    }
}
