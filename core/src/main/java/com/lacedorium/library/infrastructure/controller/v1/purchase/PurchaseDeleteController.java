package com.lacedorium.library.infrastructure.controller.v1.purchase;

import com.lacedorium.library.application.purchase.eraser.PurchaseDelete;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/purchases")
@RequiredArgsConstructor
public class PurchaseDeleteController {
    private final PurchaseDelete eraser;

    @DeleteMapping("/{purchaseId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String purchaseId) {
        eraser.dispatch(purchaseId);
    }
}
