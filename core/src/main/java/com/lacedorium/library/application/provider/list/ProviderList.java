package com.lacedorium.library.application.provider.list;

import com.lacedorium.library.application.identity.list.EntityList;
import com.lacedorium.library.domain.identity.list.Field;
import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.domain.provider.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderList extends EntityList<Provider> {
    public ProviderList(ProviderRepository repository) {
        List<Field> entityMap = List.of(
                new Field("name"),
                new Field("comercialName"),
                new Field("vatNumber"),
                new Field("website", "contact.website")
        );

        super(repository, entityMap);
    }
}
