package com.lacedorium.library.application.purchase.creator;

import com.lacedorium.library.application.purchase.creator.payload.PurchaseInvoicePayload;
import com.lacedorium.library.application.purchase.creator.payload.PurchaseLinePayload;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.BookDescriptor;
import com.lacedorium.library.domain.book.BookRepository;
import com.lacedorium.library.domain.book.exception.BookNotFoundException;
import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.domain.provider.ProviderRepository;
import com.lacedorium.library.domain.provider.exception.ProviderNotFoundException;
import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.domain.purchase.PurchaseInvoice;
import com.lacedorium.library.domain.purchase.PurchaseLine;
import com.lacedorium.library.domain.purchase.PurchaseLineRepository;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class PurchaseMakeable {
    protected final BookRepository repoBook;
    protected final ProviderRepository repoProvider;
    protected final PurchaseLineRepository repoPurchaseLine;

    protected HashMap<String, Book> bookCache;
    protected HashMap<String, BookDescriptor> bookList;

    protected PurchaseMakeable(
            BookRepository repoBook,
            ProviderRepository repoProvider,
            PurchaseLineRepository repoPurchaseLine
    ) {
        this.repoBook = repoBook;
        this.repoProvider = repoProvider;
        this.repoPurchaseLine = repoPurchaseLine;
    }

    protected void check(@NonNull List<PurchaseLinePayload> lines) {
        this.bookCache = new HashMap<>();
        this.bookList = new HashMap<>();

        // Check if the books already exist, to avoid later erros and rollbacks
        for (PurchaseLinePayload line : lines) {
            findBook(line.bookId());
        }
    }

    protected Provider findProvider(String providerId) {
        return repoProvider.obtainById(providerId)
                .orElseThrow(() -> new ProviderNotFoundException(providerId));
    }

    protected PurchaseInvoice makeInvoice(@NonNull PurchaseInvoicePayload payload) {
        return new PurchaseInvoice(
                payload.number(),
                payload.amount(),
                payload.taxes(),
                payload.total()
        );
    }

    protected void manageLines(Purchase purchase, @NonNull List<PurchaseLinePayload> lines) {
        manageLines(purchase, lines, List.of());
    }

    protected void manageLines(
            Purchase purchase,
            @NonNull List<PurchaseLinePayload> lines,
            @NonNull List<PurchaseLine> current
    ) {
        List<PurchaseLine> currentLines = new ArrayList<>(current);

        for (PurchaseLinePayload line : lines) {
            Book book = findBook(line.bookId());
            addBookDescriptor(book.getDescriptor());

            PurchaseLine purchaseLine = currentLines.stream()
                    .filter((l) -> l.getId().equals(line.lineId()))
                    .findFirst()
                    .orElse(null);

            if (null == purchaseLine) {
                purchaseLine = new PurchaseLine(
                        purchase,
                        book,
                        line.quantity(),
                        line.unitPrice(),
                        line.discountPercentage(),
                        line.total()
                );
            } else {
                currentLines.remove(purchaseLine);

                purchaseLine.modify(
                        book,
                        line.quantity(),
                        line.unitPrice(),
                        line.discountPercentage(),
                        line.total()
                );
            }

            repoPurchaseLine.save(purchaseLine);
        }

        for (PurchaseLine line : currentLines) {
            addBookDescriptor(line.getBook());
            repoPurchaseLine.remove(line.getId());
        }
    }

    protected Book findBook(String bookId) {
        if (bookCache.containsKey(bookId)) {
            return bookCache.get(bookId);
        }

        Book book = repoBook.obtainById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        bookCache.put(bookId, book);
        return book;
    }

    protected void addBookDescriptor(@NonNull BookDescriptor descriptor) {
        if (!bookList.containsKey(descriptor.getId())) {
            bookList.put(descriptor.getId(), descriptor);
        }
    }

    protected List<BookDescriptor> getBookList() {
        return List.copyOf(bookList.values());
    }

    protected void clearCache() {
        bookCache.clear();
        bookList.clear();
    }
}
