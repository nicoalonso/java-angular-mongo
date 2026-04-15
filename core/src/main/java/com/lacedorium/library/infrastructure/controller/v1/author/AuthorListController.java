package com.lacedorium.library.infrastructure.controller.v1.author;

import com.lacedorium.library.application.author.list.AuthorList;
import com.lacedorium.library.application.author.list.AuthorListQuery;
import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.domain.identity.list.ListQuery;
import com.lacedorium.library.domain.identity.list.ListResult;
import com.lacedorium.library.presentation.v1.author.AuthorReadRecord;
import com.lacedorium.library.presentation.v1.identity.ListView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/authors")
@RequiredArgsConstructor
public class AuthorListController {
    private final AuthorList lister;

    @GetMapping()
    public ListView<AuthorReadRecord> listAuthors(@ModelAttribute AuthorListQuery queryParams) {
        ListQuery query = ListQuery.parse(queryParams);
        ListResult<Author> result = lister.dispatch(query);
        return ListView.of(result, AuthorReadRecord::make);
    }
}
