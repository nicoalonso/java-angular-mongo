package com.lacedorium.library.application.sale.creator;

import com.lacedorium.library.application.sale.creator.payload.SaleInvoicePayload;
import com.lacedorium.library.application.sale.creator.payload.SaleLinePayload;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.BookDescriptor;
import com.lacedorium.library.domain.book.BookRepository;
import com.lacedorium.library.domain.book.exception.BookNotFoundException;
import com.lacedorium.library.domain.bus.DomainBus;
import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.domain.customer.CustomerRepository;
import com.lacedorium.library.domain.customer.exception.CustomerNotFoundException;
import com.lacedorium.library.domain.sale.*;
import com.lacedorium.library.domain.sequence.SequenceNumber;
import com.lacedorium.library.domain.sequence.SequenceNumberRepository;
import com.lacedorium.library.domain.sequence.SequenceType;
import com.lacedorium.library.domain.user.User;
import com.lacedorium.library.domain.user.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleCreate {
    private final SaleRepository repoSale;
    private final SaleLineRepository repoSaleLine;
    private final CustomerRepository repoCustomer;
    private final BookRepository repoBook;
    private final SequenceNumberRepository repoSequenceNumber;
    private final UserRepository repoUser;
    private final DomainBus bus;

    private HashMap<String, Book> bookCache;
    private HashMap<String, BookDescriptor> bookList;

     public Sale dispatch(@NonNull SaleCreatePayload payload) {
         bookCache = new HashMap<>();
         bookList = new HashMap<>();

         check(payload);

         Customer customer = findCustomer(payload.customerId());
         SaleInvoice invoice = makeInvoice(payload.invoice());
         String number = generateInvoiceNextNumber();
         User user = repoUser.obtainUser();

         Sale sale = new Sale(
                 customer,
                 number,
                 invoice,
                 user.getName()
         );
         repoSale.save(sale);

         manageLines(sale, payload.lines());

         SaleCreatedEvent event = new SaleCreatedEvent(sale, getBookList());
         bus.dispatch(event);

         clearCache();

         return sale;
     }

     private void check(@NonNull SaleCreatePayload payload) {
         if (payload.lines().isEmpty()) {
             throw new SaleLinesEmptyException();
         }

         // Check if the books already exist to avoid later errors and rollbacks
         for (SaleLinePayload line : payload.lines()) {
             findBook(line.bookId());
         }
     }

     private @NonNull Customer findCustomer(String customerId) {
         return repoCustomer.obtainById(customerId)
                 .orElseThrow(() -> new CustomerNotFoundException(customerId));
     }

     private @NonNull SaleInvoice makeInvoice(@NonNull SaleInvoicePayload payload) {
        return new SaleInvoice(
                payload.getDate(),
                payload.amount(),
                payload.taxPercentage(),
                payload.taxes(),
                payload.total()
        );
     }

    private String generateInvoiceNextNumber() {
        String number;

        do {
            SequenceNumber sequenceNumber = repoSequenceNumber.nextNumber(SequenceType.SALE);
            number = sequenceNumber.format();

        } while (repoSale.obtainByNumber(number).isPresent());

        return number;
    }

    private void manageLines(Sale sale, @NonNull List<SaleLinePayload> lines) {
        for (SaleLinePayload line : lines) {
            Book book = findBook(line.bookId());
            addBookDescriptor(book.getDescriptor());

            SaleLine saleLine = new SaleLine(
                    sale,
                    book,
                    line.quantity(),
                    line.price(),
                    line.discount(),
                    line.total()
            );
            repoSaleLine.save(saleLine);
        }
    }

    private Book findBook(String bookId) {
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
