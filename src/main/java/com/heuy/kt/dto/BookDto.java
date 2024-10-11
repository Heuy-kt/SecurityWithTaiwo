package com.heuy.kt.dto;

import com.heuy.kt.models.Plan;
import lombok.Builder;

@Builder
public record BookDto(
        String title,
        String description,
        String author,
        Plan plan
) {
}
