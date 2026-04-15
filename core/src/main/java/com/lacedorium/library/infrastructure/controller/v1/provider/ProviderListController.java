package com.lacedorium.library.infrastructure.controller.v1.provider;

import com.lacedorium.library.application.provider.list.ProviderList;
import com.lacedorium.library.application.provider.list.ProviderListQuery;
import com.lacedorium.library.domain.identity.list.ListQuery;
import com.lacedorium.library.domain.identity.list.ListResult;
import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.presentation.v1.identity.ListView;
import com.lacedorium.library.presentation.v1.provider.ProviderReadRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/providers")
@RequiredArgsConstructor
public class ProviderListController {
    private final ProviderList lister;

    @GetMapping()
    public ListView<ProviderReadRecord> listProviders(@ModelAttribute ProviderListQuery queryParams) {
        ListQuery query = ListQuery.parse(queryParams);
        ListResult<Provider> result = lister.dispatch(query);
        return ListView.of(result, ProviderReadRecord::make);
    }
}
