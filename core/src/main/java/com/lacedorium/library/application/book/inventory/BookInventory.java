package com.lacedorium.library.application.book.inventory;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.BookDescriptor;
import com.lacedorium.library.domain.book.BookRepository;
import com.lacedorium.library.domain.book.exception.BookNotFoundException;
import com.lacedorium.library.domain.purchase.PurchaseLine;
import com.lacedorium.library.domain.purchase.PurchaseLineRepository;
import com.lacedorium.library.domain.sale.SaleLine;
import com.lacedorium.library.domain.sale.SaleLineRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookInventory {
    private final BookRepository repoBook;
    private final PurchaseLineRepository repoPurchaseLine;
    private final SaleLineRepository repoSaleLine;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void dispatch(@NonNull BookDescriptor descriptor) {
        logger.info("Calculating inventory for book: {}, {}", descriptor.getId(), descriptor.getTitle());
        Integer stock = 0;

        Book book = repoBook.obtainById(descriptor.getId())
                .orElseThrow(() -> new BookNotFoundException(descriptor.getId()));

        List<PurchaseLine> purchaseLines = repoPurchaseLine.obtainByBook(book.getId());
        logger.info("Found {} purchases lines for book: {}", purchaseLines.size(), book.getId());

        for (PurchaseLine purchaseLine : purchaseLines) {
            stock += purchaseLine.getQuantity();
        }

        List<SaleLine> saleLines = repoSaleLine.obtainByBook(book.getId());
        logger.info("Found {} sale lines for book: {}", saleLines.size(), book.getId());

        for (SaleLine saleLine : saleLines) {
            stock -= saleLine.getQuantity();
        }

        logger.info("Final stock for book: {}, {}", book.getId(), stock);
        if (stock < 0) {
            logger.warn("Negative stock for book: {}, {}", book.getId(), stock);
            stock = 0;
        }

        book.changeStock(stock);
        repoBook.save(book);
    }
}
