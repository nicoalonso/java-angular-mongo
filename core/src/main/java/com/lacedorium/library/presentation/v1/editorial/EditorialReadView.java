package com.lacedorium.library.presentation.v1.editorial;

import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.presentation.v1.identity.Result;

public class EditorialReadView extends Result<EditorialReadRecord, Editorial> {
    public EditorialReadView(Editorial editorial) {
        super(editorial, EditorialReadRecord::make);
    }
}
