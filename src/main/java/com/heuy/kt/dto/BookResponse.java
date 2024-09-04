package com.heuy.kt.dto;

import lombok.Builder;

@Builder
public record BookResponse(
        String title,
        String description,
        String author,
        String plan
)
{}
