package com.lacedorium.library.infrastructure.controller.v1.editorial;

import com.lacedorium.library.application.editorial.creator.EditorialCreate;
import com.lacedorium.library.application.editorial.creator.EditorialCreatePayload;
import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.presentation.v1.editorial.EditorialReadView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/editorials")
@RequiredArgsConstructor
public class EditorialCreateController {
    private final EditorialCreate creator;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public EditorialReadView create(@RequestBody EditorialCreatePayload payload) {
        Editorial editorial = creator.dispatch(payload);
        return new EditorialReadView(editorial);
    }
}
