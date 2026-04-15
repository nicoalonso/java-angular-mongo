package com.lacedorium.library.application.editorial.list;

import com.lacedorium.library.domain.identity.list.ListQueryPayload;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EditorialListQuery extends ListQueryPayload {
    String name;
    String comercialName;
    String website;
}
