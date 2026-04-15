package com.lacedorium.library.application.editorial.eraser;

import com.lacedorium.library.domain.book.BookRepository;
import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.domain.editorial.EditorialRepository;
import com.lacedorium.library.domain.editorial.exception.EditorialNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EditorialDelete {
    private final EditorialRepository repoEditorial;
    private final BookRepository repoBook;

    public void dispatch(String editorialId) {
        Editorial editorial = repoEditorial.obtainById(editorialId)
                .orElseThrow(() -> new EditorialNotFoundException(editorialId));

        searchBooksRelated(editorial);

        repoEditorial.remove(editorialId);
    }

    private void searchBooksRelated(@NonNull Editorial editorial) {
        List<?> books = repoBook.obtainByEditorial(editorial.getId(), 1);
        if (!books.isEmpty()) {
            throw new EditorialBookAssociatedException(editorial.getId());
        }
    }
}
