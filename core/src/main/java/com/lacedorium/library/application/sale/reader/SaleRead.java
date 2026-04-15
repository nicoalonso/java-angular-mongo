package com.lacedorium.library.application.sale.reader;

import com.lacedorium.library.domain.sale.Sale;
import com.lacedorium.library.domain.sale.SaleLine;
import com.lacedorium.library.domain.sale.SaleLineRepository;
import com.lacedorium.library.domain.sale.SaleRepository;
import com.lacedorium.library.domain.sale.exception.SaleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleRead {
    private final SaleRepository repoSale;
    private final SaleLineRepository repoSaleLine;

    public SaleDecorator dispatch(String saleId) {
        Sale sale = repoSale.obtainById(saleId)
                .orElseThrow(() -> new SaleNotFoundException(saleId));

        List<SaleLine> lines = repoSaleLine.obtainBySale(saleId);

        return new SaleDecorator(sale, lines);
    }
}
