package com.lacedorium.library.application.borrow.creator;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.BookRepository;
import com.lacedorium.library.domain.book.exception.BookNotFoundException;
import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.domain.borrow.BorrowLine;
import com.lacedorium.library.domain.borrow.BorrowLineRepository;
import com.lacedorium.library.domain.borrow.BorrowRepository;
import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.domain.customer.CustomerRepository;
import com.lacedorium.library.domain.customer.exception.CustomerNotFoundException;
import com.lacedorium.library.domain.sequence.SequenceNumber;
import com.lacedorium.library.domain.sequence.SequenceNumberRepository;
import com.lacedorium.library.domain.sequence.SequenceType;
import com.lacedorium.library.domain.user.User;
import com.lacedorium.library.domain.user.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class BorrowCreate {
    private final BorrowRepository repoBorrow;
    private final BorrowLineRepository repoBorrowLine;
    private final CustomerRepository repoCustomer;
    private final BookRepository repoBook;
    private final SequenceNumberRepository repoSequenceNumber;
    private final UserRepository repoUser;

    private HashMap<String, Book> bookCache;

    public Borrow dispatch(@NonNull BorrowCreatePayload payload) {
        bookCache = new HashMap<>();

        check(payload);

        Customer customer = findCustomer(payload.customerId());
        User user = repoUser.obtainUser();
        String number = generateInvoiceNextNumber();

        Borrow borrow = new Borrow(
                customer,
                number,
                payload.lines().size(),
                user.getName()
        );
        repoBorrow.save(borrow);

        manageLines(borrow, payload.lines());

        // Clear cache
        bookCache.clear();

        return borrow;
    }

    private void check(@NonNull BorrowCreatePayload payload) {
        if (payload.lines().isEmpty()) {
            throw new BorrowLinesEmptyException();
        }

        // Check if the books already exist to avoid later errors and rollbacks
        for (BorrowLinePayload line : payload.lines()) {
            findBook(line.bookId());
        }
    }

    private @NonNull Customer findCustomer(String customerId) {
        return repoCustomer.obtainById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    private String generateInvoiceNextNumber() {
        String number;

        do {
            SequenceNumber sequenceNumber = repoSequenceNumber.nextNumber(SequenceType.BORROW);
            number = sequenceNumber.format();

        } while (repoBorrow.obtainByNumber(number).isPresent());

        return number;
    }

    private void manageLines(Borrow borrow, @NonNull Iterable<BorrowLinePayload> lines) {
        for (BorrowLinePayload line : lines) {
            Book book = findBook(line.bookId());
            BorrowLine borrowLine = new BorrowLine(borrow, book);
            repoBorrowLine.save(borrowLine);
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
}
