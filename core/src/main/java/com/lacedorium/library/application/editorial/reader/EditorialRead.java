package com.lacedorium.library.application.editorial.reader;

import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.domain.editorial.EditorialRepository;
import com.lacedorium.library.domain.editorial.exception.EditorialNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditorialRead {
    private final EditorialRepository repoEditorial;

    public Editorial dispatch(String editorialId) {
        return repoEditorial.obtainById(editorialId)
                .orElseThrow(() -> new EditorialNotFoundException(editorialId));
    }
}
