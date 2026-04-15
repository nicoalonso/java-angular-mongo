package com.lacedorium.library.application.editorial.list;

import com.lacedorium.library.application.identity.list.EntityList;
import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.domain.editorial.EditorialRepository;
import com.lacedorium.library.domain.identity.list.Field;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorialList extends EntityList<Editorial> {
    public EditorialList(EditorialRepository repository) {
        List<Field> entityMap = List.of(
                new Field("name"),
                new Field("comercialName"),
                new Field("website", "contact.website")
        );

        super(repository, entityMap);
    }
}
