package com.lacedorium.library.infrastructure.controller.v1.editorial;

import com.lacedorium.library.application.editorial.list.EditorialList;
import com.lacedorium.library.application.editorial.list.EditorialListQuery;
import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.domain.identity.list.ListQuery;
import com.lacedorium.library.domain.identity.list.ListResult;
import com.lacedorium.library.presentation.v1.editorial.EditorialReadRecord;
import com.lacedorium.library.presentation.v1.identity.ListView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/editorials")
@RequiredArgsConstructor
public class EditorialListController {
    private final EditorialList lister;

    @GetMapping()
    public ListView<EditorialReadRecord> listEditorials(@ModelAttribute EditorialListQuery queryParams) {
        ListQuery query = ListQuery.parse(queryParams);
        ListResult<Editorial> result = lister.dispatch(query);
        return ListView.of(result, EditorialReadRecord::make);
    }
}
