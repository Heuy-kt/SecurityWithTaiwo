package com.heuy.kt.dto;

import lombok.Builder;

@Builder
public record CustomerRequest(
        String name,
        String email,
        String password,
        String role
){}
