package com.heuy.kt.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String username,
        String token
) {}
