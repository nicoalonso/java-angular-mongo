package com.lacedorium.library.application.purchase.list;

import com.lacedorium.library.domain.identity.list.ListQueryPayload;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseListQuery extends ListQueryPayload {
    String provider;
    String fromPurchaseAt;
    String toPurchaseAt;
    String invoiceNumber;
}
