package com.lacedorium.library.domain.book;

import com.lacedorium.library.domain.identity.IdentityRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends IdentityRepository<Book> {
    Optional<Book> obtainByTitle(String title);
    List<Book> obtainByAuthor(String authorId);
    List<Book> obtainByAuthor(String authorId, int limit);
    List<Book> obtainByEditorial(String editorialId);
    List<Book> obtainByEditorial(String editorialId, int limit);
}
