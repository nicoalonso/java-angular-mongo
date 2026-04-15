package com.lacedorium.library.application.provider.list;

import com.lacedorium.library.domain.identity.list.ListQueryPayload;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProviderListQuery extends ListQueryPayload {
    String name;
    String comercialName;
    String vatNumber;
    String website;
}
