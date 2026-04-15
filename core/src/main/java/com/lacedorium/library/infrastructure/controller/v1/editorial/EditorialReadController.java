package com.lacedorium.library.infrastructure.controller.v1.editorial;

import com.lacedorium.library.application.editorial.reader.EditorialRead;
import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.presentation.v1.editorial.EditorialReadView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/editorials")
@RequiredArgsConstructor
public class EditorialReadController {
    private final EditorialRead reader;

    @GetMapping("/{editorialId}")
    public EditorialReadView dispatch(@PathVariable String editorialId) {
        Editorial editorial = reader.dispatch(editorialId);
        return new EditorialReadView(editorial);
    }
}
