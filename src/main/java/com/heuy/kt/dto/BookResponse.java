package com.heuy.kt.dto;

public record BookRequest(
        String title,
        String description,
        String author,
        String plan
)
{}
