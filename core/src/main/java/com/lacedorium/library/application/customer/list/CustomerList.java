package com.lacedorium.library.application.customer.list;

import com.lacedorium.library.application.identity.list.EntityList;
import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.domain.customer.CustomerRepository;
import com.lacedorium.library.domain.identity.list.Field;
import com.lacedorium.library.domain.identity.list.FilterType;
import com.lacedorium.library.domain.identity.list.ValueKind;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerList extends EntityList<Customer> {
    public CustomerList(CustomerRepository repository) {
        List<Field> entityMap = List.of(
                new Field("name"),
                new Field("surname"),
                new Field("number", "membership.number"),
                new Field("active", "membership.active", FilterType.MATCH, ValueKind.BOOLEAN),
                new Field("city", "address.city"),
                new Field("vatNumber")
        );

        super(repository, entityMap);
    }
}
