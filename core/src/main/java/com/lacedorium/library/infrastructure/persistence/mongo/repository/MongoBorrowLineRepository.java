package com.lacedorium.library.infrastructure.persistence.mongo.repository;

import com.lacedorium.library.domain.borrow.BorrowLine;
import com.lacedorium.library.domain.borrow.BorrowLineRepository;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.BorrowLineDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MongoBorrowLineRepository
        extends MongoRepository<BorrowLine, BorrowLineDocument>
        implements BorrowLineRepository {
    public MongoBorrowLineRepository(MongoTemplate client) {
        super(client, BorrowLine.class, BorrowLineDocument.class);
    }

    @Override
    public List<BorrowLine> obtainByBorrow(String borrowId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("borrow", borrowId);

        return findBy(params);
    }

    @Override
    public List<BorrowLine> obtainByBook(String bookId) {
        return obtainByBook(bookId, 0);
    }

    @Override
    public List<BorrowLine> obtainByBook(String bookId, Integer limit) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("book.id", bookId);

        return findBy(params);
    }

    @Override
    public List<BorrowLine> obtainActiveByBook(String bookId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("book.id", bookId);
        params.put("returned", false);

        return findBy(params);
    }
}
