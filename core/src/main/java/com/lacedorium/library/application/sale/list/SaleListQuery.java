package com.lacedorium.library.application.sale.list;

import com.lacedorium.library.domain.identity.list.ListQueryPayload;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaleListQuery extends ListQueryPayload {
    String customer;
    String fromDate;
    String toDate;
    String number;
}
