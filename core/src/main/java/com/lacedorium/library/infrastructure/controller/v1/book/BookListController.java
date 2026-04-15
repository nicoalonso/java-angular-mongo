package com.lacedorium.library.infrastructure.controller.v1.book;

import com.lacedorium.library.application.book.list.BookList;
import com.lacedorium.library.application.book.list.BookListQuery;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.identity.list.ListQuery;
import com.lacedorium.library.domain.identity.list.ListResult;
import com.lacedorium.library.presentation.v1.book.BookReadRecord;
import com.lacedorium.library.presentation.v1.identity.ListView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/books")
@RequiredArgsConstructor
public class BookListController {
    private final BookList lister;

    @GetMapping()
    public ListView<BookReadRecord> listBooks(@ModelAttribute BookListQuery queryParams) {
        ListQuery query = ListQuery.parse(queryParams);
        ListResult<Book> result = lister.dispatch(query);
        return ListView.of(result, BookReadRecord::make);
    }
}
