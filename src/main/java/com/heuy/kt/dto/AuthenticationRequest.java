package com.heuy.kt.dto;

public record AuthenticationRequest(
        String email,
        String password
){}
