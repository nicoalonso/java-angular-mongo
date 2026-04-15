package com.lacedorium.library.application.provider.creator;

import com.lacedorium.library.domain.common.Address;
import com.lacedorium.library.domain.common.EnterpriseContact;
import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.domain.provider.ProviderRepository;
import com.lacedorium.library.domain.user.User;
import com.lacedorium.library.domain.user.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderCreate {
    private final ProviderRepository repoProvider;
    private final UserRepository repoUser;

    public Provider dispatch(@NonNull ProviderCreatePayload payload) {
        checkAlreadyExists(payload);

        User user = repoUser.obtainUser();
        Address address = new Address(
                payload.address().street(),
                payload.address().postalCode(),
                payload.address().city(),
                payload.address().province(),
                payload.address().country()
        );
        EnterpriseContact contact = new EnterpriseContact(
                payload.contact().email(),
                payload.contact().website(),
                payload.contact().phone1(),
                payload.contact().phone2()
        );

        Provider provider = new Provider(
                payload.name(),
                payload.comercialName(),
                contact,
                address,
                payload.vatNumber(),
                user.getName()
        );

        repoProvider.save(provider);
        return provider;
    }

    private void checkAlreadyExists(@NonNull ProviderCreatePayload payload) {
        if (repoProvider.obtainByName(payload.name()).isPresent()) {
            throw new ProviderAlreadyExistsException(payload.name());
        }
    }
}
