package com.lacedorium.library.application.sale.reader;

import com.lacedorium.library.domain.sale.Sale;
import com.lacedorium.library.domain.sale.SaleLine;

import java.util.List;

public record SaleDecorator (Sale sale, List<SaleLine> lines) {
}
