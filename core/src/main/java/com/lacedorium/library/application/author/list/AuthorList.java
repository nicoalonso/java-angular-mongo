package com.lacedorium.library.application.author.list;

import com.lacedorium.library.application.identity.list.EntityList;
import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.domain.author.AuthorRepository;
import com.lacedorium.library.domain.identity.list.Field;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorList extends EntityList<Author> {
    public AuthorList(AuthorRepository repository) {
        List<Field> entityMap = List.of(
                new Field("name"),
                new Field("realName"),
                new Field("nationality")
        );

        super(repository, entityMap);
    }
}
