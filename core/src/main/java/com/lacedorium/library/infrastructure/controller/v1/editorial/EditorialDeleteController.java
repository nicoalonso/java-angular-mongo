package com.lacedorium.library.infrastructure.controller.v1.editorial;

import com.lacedorium.library.application.editorial.eraser.EditorialDelete;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/editorials")
@RequiredArgsConstructor
public class EditorialDeleteController {
    private final EditorialDelete eraser;

    @DeleteMapping("/{editorialId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteEditorial(@PathVariable String editorialId) {
        eraser.dispatch(editorialId);
    }
}
