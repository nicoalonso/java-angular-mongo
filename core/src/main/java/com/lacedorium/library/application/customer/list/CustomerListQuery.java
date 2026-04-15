package com.lacedorium.library.application.customer.list;

import com.lacedorium.library.domain.identity.list.ListQueryPayload;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( callSuper = true)
public class CustomerListQuery extends ListQueryPayload {
    String name;
    String surname;
    String number;
    Boolean active;
    String city;
    String vatNumber;
}
