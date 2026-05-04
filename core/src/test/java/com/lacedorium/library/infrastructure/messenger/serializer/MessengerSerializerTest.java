package com.lacedorium.library.infrastructure.messenger.serializer;

import com.lacedorium.library.application.book.inventory.BookInventoryEvent;
import com.lacedorium.library.application.borrow.sanctioner.BorrowPenaltyEvent;
import com.lacedorium.library.application.sale.creator.SaleCreatedEvent;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.sale.Sale;
import com.lacedorium.library.fixtures.mothers.BookMother;
import com.lacedorium.library.fixtures.mothers.SaleMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessengerSerializerTest {
    private MessengerSerializer serializer;

    @BeforeEach
    void setUp() {
        serializer = new MessengerSerializer();
    }

    @Test
    void shouldFailWhenInvalidEventOnEncode() {
        Sale sale = SaleMother.johnDoeSale2().build();
        SaleCreatedEvent event = new SaleCreatedEvent(sale, List.of());

        assertThrows(IllegalArgumentException.class, () -> serializer.encode(event));
    }

    @Test
    void shouldEncodeWhenBookInventory() {
        Book book = BookMother.donQuixote().build();
        BookInventoryEvent event = new BookInventoryEvent(book.getDescriptor());

        Envelope<?> result = serializer.encode(event);

        assertNotNull(result);
        assertEquals("book.inventory", result.getAction());
        assertEquals("book", result.getType());
    }

    @Test
    void shouldEncodeWhenBorrowPenalty() {
        BorrowPenaltyEvent event = new BorrowPenaltyEvent();

        Envelope<?> result = serializer.encode(event);

        assertNotNull(result);
        assertEquals("borrow.penalty", result.getAction());
        assertEquals("borrow", result.getType());
    }

    @Test
    void shouldFailWhenDecoded() {
        Envelope<LinkedHashMap<String, Object>> envelope = new Envelope<>("invalid.action", "invalid.type", new LinkedHashMap<>());

        assertThrows(IllegalArgumentException.class, () -> serializer.decode(envelope));
    }

    @Test
    void shouldRunWhenDecodeBorrowPenalty() {
        Envelope<LinkedHashMap<String, Object>> envelope = new Envelope<>("borrow.penalty", "borrow", new LinkedHashMap<>());

        var event = serializer.decode(envelope);

        assertNotNull(event);
        assertEquals(BorrowPenaltyEvent.class, event.getClass());
    }

    @Test
    void shouldRunWhenDecodeBookInventory() {
        LinkedHashMap<String, Object> payload = new LinkedHashMap<>();
        payload.put("id", "123");
        payload.put("title", "Don Quixote");
        payload.put("isbn", "989-999988899");

        Envelope<LinkedHashMap<String, Object>> envelope = new Envelope<>("book.inventory", "book", payload);

        var event = serializer.decode(envelope);

        assertNotNull(event);
        assertEquals(BookInventoryEvent.class, event.getClass());
        assertEquals("123", ((BookInventoryEvent) event).getDescriptor().getId());
    }
}