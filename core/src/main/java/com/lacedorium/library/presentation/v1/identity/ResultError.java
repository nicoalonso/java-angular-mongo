package com.lacedorium.library.presentation.v1.identity;

public record ResultError (
    int code,
    String message
) {}
