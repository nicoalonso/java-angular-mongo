package com.lacedorium.library.application.editorial.updater;

import com.lacedorium.library.domain.common.Address;
import com.lacedorium.library.domain.common.EnterpriseContact;
import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.domain.editorial.EditorialRepository;
import com.lacedorium.library.domain.editorial.exception.EditorialNotFoundException;
import com.lacedorium.library.domain.user.User;
import com.lacedorium.library.domain.user.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditorialUpdate {
    private final EditorialRepository repoEditorial;
    private final UserRepository repoUser;

    public @NonNull Editorial dispatch(String editorialId, @NonNull EditorialUpdatePayload payload) {
        Editorial editorial = repoEditorial.obtainById(editorialId)
                .orElseThrow(() -> new EditorialNotFoundException(editorialId));

        EnterpriseContact contact = new EnterpriseContact(
                payload.contact().email(),
                payload.contact().website(),
                payload.contact().phone1(),
                payload.contact().phone2()
        );
        Address address = new Address(
                payload.address().street(),
                payload.address().postalCode(),
                payload.address().city(),
                payload.address().province(),
                payload.address().country()
        );

        User user = repoUser.obtainUser();
        editorial.modify(
                payload.name(),
                payload.comercialName(),
                contact,
                address,
                user.getName()
        );

        repoEditorial.save(editorial);
        return editorial;
    }
}
