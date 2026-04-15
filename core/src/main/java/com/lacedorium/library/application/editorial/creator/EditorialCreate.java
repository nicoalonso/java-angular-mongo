package com.lacedorium.library.application.editorial.creator;

import com.lacedorium.library.domain.common.Address;
import com.lacedorium.library.domain.common.EnterpriseContact;
import com.lacedorium.library.domain.editorial.EditorialRepository;
import com.lacedorium.library.domain.user.User;
import com.lacedorium.library.domain.user.UserRepository;
import com.lacedorium.library.domain.editorial.Editorial;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditorialCreate {
    private final EditorialRepository repoEditorial;
    private final UserRepository repoUser;

    public @NonNull Editorial dispatch(@NonNull EditorialCreatePayload payload) {
        checkAlreadyExists(payload);

        User user = repoUser.obtainUser();
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

        Editorial editorial = new Editorial(
                payload.name(),
                payload.comercialName(),
                contact,
                address,
                user.getName()
        );

        repoEditorial.save(editorial);
        return editorial;
    }

    private void checkAlreadyExists(@NonNull EditorialCreatePayload payload) {
        if (repoEditorial.obtainByName(payload.name()).isPresent()) {
            throw new EditorialAlreadyExistsException(payload.name());
        }
    }
}
