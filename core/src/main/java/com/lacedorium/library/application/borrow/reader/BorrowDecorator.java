package com.lacedorium.library.application.borrow.reader;

import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.domain.borrow.BorrowLine;

import java.util.List;

public record BorrowDecorator (Borrow borrow, List<BorrowLine> lines) {
}
