package com.lacedorium.library.application.provider.updater;

import com.lacedorium.library.domain.common.Address;
import com.lacedorium.library.domain.common.EnterpriseContact;
import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.domain.provider.ProviderRepository;
import com.lacedorium.library.domain.provider.exception.ProviderNotFoundException;
import com.lacedorium.library.domain.user.User;
import com.lacedorium.library.domain.user.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderUpdate {
    private final ProviderRepository repoProvider;
    private final UserRepository repoUser;

    public Provider dispatch(String providerId, @NonNull ProviderUpdatePayload payload) {
        Provider provider = repoProvider.obtainById(providerId)
                .orElseThrow(() -> new ProviderNotFoundException(providerId));

        Address address = new Address(
                payload.address().street(),
                payload.address().postalCode(),
                payload.address().city(),
                payload.address().province(),
                payload.address().country()
        );
        User user = repoUser.obtainUser();
        EnterpriseContact contact = new EnterpriseContact(
                payload.contact().email(),
                payload.contact().website(),
                payload.contact().phone1(),
                payload.contact().phone2()
        );

        provider.modify(
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
}
