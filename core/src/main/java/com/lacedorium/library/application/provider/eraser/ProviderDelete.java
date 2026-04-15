package com.lacedorium.library.application.provider.eraser;

import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.domain.provider.ProviderRepository;
import com.lacedorium.library.domain.provider.exception.ProviderNotFoundException;
import com.lacedorium.library.domain.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderDelete {
    private final ProviderRepository repoProvider;
    private final PurchaseRepository repoPurchase;

    public void dispatch(String providerId) {
        Provider provider = repoProvider.obtainById(providerId)
                .orElseThrow(() -> new ProviderNotFoundException(providerId));

        List<?> purchases = repoPurchase.obtainByProvider(providerId, 1);
        if (!purchases.isEmpty()) {
            throw new ProviderAssociatedException();
        }

        repoProvider.remove(provider.getId());
    }
}
