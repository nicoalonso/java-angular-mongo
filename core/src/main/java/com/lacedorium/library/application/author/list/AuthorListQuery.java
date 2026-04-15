package com.lacedorium.library.application.author.list;

import com.lacedorium.library.domain.identity.list.ListQueryPayload;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorListQuery extends ListQueryPayload {
    String name;
    String realName;
    String nationality;
}
