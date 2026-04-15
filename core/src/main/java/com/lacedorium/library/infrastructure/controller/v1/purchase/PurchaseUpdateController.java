package com.lacedorium.library.infrastructure.controller.v1.purchase;

import com.lacedorium.library.application.purchase.updater.PurchaseUpdate;
import com.lacedorium.library.application.purchase.updater.PurchaseUpdatePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/purchases")
@RequiredArgsConstructor
public class PurchaseUpdateController {
    private final PurchaseUpdate updater;

    @PutMapping("/{purchaseId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void update(@PathVariable String purchaseId, @RequestBody PurchaseUpdatePayload payload) {
        updater.dispatch(purchaseId, payload);
    }
}
