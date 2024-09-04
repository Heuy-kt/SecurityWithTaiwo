package com.heuy.kt.dto;

import com.heuy.kt.models.Role;
import lombok.Builder;


@Builder
public record CustomerResponse(
        String token,
        String email,
        String name,
        String password,
        Role role

){}
