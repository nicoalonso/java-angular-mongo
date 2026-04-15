package com.lacedorium.library.infrastructure.controller.v1.borrow;

import com.lacedorium.library.application.borrow.list.BorrowList;
import com.lacedorium.library.application.borrow.list.BorrowListQuery;
import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.domain.identity.list.ListQuery;
import com.lacedorium.library.domain.identity.list.ListResult;
import com.lacedorium.library.presentation.v1.borrow.BorrowListRecord;
import com.lacedorium.library.presentation.v1.identity.ListView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/borrows")
@RequiredArgsConstructor
public class BorrowListController {
    private final BorrowList lister;

    @GetMapping()
    public ListView<BorrowListRecord> listBorrows(@ModelAttribute BorrowListQuery queryParams) {
        ListQuery query = ListQuery.parse(queryParams);
        ListResult<Borrow> result = lister.dispatch(query);
        return ListView.of(result, BorrowListRecord::make);
    }
}
