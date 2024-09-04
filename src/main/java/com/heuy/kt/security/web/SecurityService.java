package com.heuy.kt.security.web;

import com.heuy.kt.dto.AuthenticationRequest;
import com.heuy.kt.dto.AuthenticationResponse;
import com.heuy.kt.dto.CustomerRequest;
import com.heuy.kt.dto.CustomerResponse;
import com.heuy.kt.exception.NotFoundException;
import com.heuy.kt.models.Customer;
import com.heuy.kt.models.Role;
import com.heuy.kt.repo.CustomerRepo;
import com.heuy.kt.security.jwt.JWTService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SecurityService {

    private final CustomerRepo customerRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerAccount (CustomerRequest customerRequest){
        if(customerRepo.findByEmail(customerRequest.email()).isPresent()){
            return new AuthenticationResponse(customerRequest.email(), "already exists");
        }
        Customer customer = Customer
                .builder()
                .name(customerRequest.name())
                .email(customerRequest.email())
                .password(passwordEncoder.encode(customerRequest.password()))
                .role(Role.valueOf(customerRequest.role()))
                .build();

        customerRepo.save(customer);

        String token = jwtService.generateToken(customer);
        return AuthenticationResponse
                .builder()
                .username(customer.getName())
                .token(token)
                .build();
    }

    public CustomerResponse loginAccount(AuthenticationRequest customerRequest){
        Customer customer = customerRepo.findByEmail(customerRequest.email())
                .orElseThrow(
                        () -> new NotFoundException("User not found", 404)
                        );

        authenticationManager.authenticate(new  UsernamePasswordAuthenticationToken(
                customerRequest.email(), customerRequest.password()
                )
        );
        String token = jwtService.generateToken(customer);
        return CustomerResponse.builder()
                .token(token)
                .email(customer.getEmail())
                .name(customer.getName())
                .role(customer.getRole())
                .build();
    }
}
