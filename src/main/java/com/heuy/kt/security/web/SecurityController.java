package com.heuy.kt.security.web;


import com.heuy.kt.dto.AuthenticationRequest;
import com.heuy.kt.dto.AuthenticationResponse;
import com.heuy.kt.dto.CustomerRequest;
import com.heuy.kt.dto.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("security")
public class SecurityController {
    private final SecurityService securityService;

    @GetMapping("login")
    public ResponseEntity<CustomerResponse> login(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(securityService.loginAccount(authenticationRequest));
    }

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody CustomerRequest customerRequest){
        return ResponseEntity.ok(securityService.registerAccount(customerRequest));
    }


}
