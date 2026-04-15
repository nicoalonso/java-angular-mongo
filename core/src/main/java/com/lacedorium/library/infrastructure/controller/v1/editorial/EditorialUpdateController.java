package com.lacedorium.library.infrastructure.controller.v1.editorial;

import com.lacedorium.library.application.editorial.updater.EditorialUpdate;
import com.lacedorium.library.application.editorial.updater.EditorialUpdatePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/editorials")
@RequiredArgsConstructor
public class EditorialUpdateController {
    private final EditorialUpdate updater;

    @PutMapping("/{editorialId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void update(@PathVariable String editorialId, @RequestBody EditorialUpdatePayload request) {
        updater.dispatch(editorialId, request);
    }
}
