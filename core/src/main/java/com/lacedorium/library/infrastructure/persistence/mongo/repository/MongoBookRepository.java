package com.lacedorium.library.infrastructure.persistence.mongo.repository;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.BookRepository;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.BookDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class MongoBookRepository extends MongoRepository<Book, BookDocument> implements BookRepository {
    public MongoBookRepository(MongoTemplate client) {
        super(client, Book.class, BookDocument.class);
    }

    @Override
    public Optional<Book> obtainByTitle(String title) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("title", title);

        return findOneBy(params);
    }

    @Override
    public List<Book> obtainByAuthor(String authorId) {
        return obtainByAuthor(authorId, 0);
    }

    @Override
    public List<Book> obtainByAuthor(String authorId, int limit) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("author.id", authorId);

        return findBy(params, limit);
    }

    @Override
    public List<Book> obtainByEditorial(String editorialId) {
        return obtainByEditorial(editorialId, 0);
    }

    @Override
    public List<Book> obtainByEditorial(String editorialId, int limit) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("editorial.id", editorialId);

        return findBy(params, limit);
    }
}
