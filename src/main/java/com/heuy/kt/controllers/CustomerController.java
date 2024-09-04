package com.heuy.kt.controllers;

import com.heuy.kt.dto.CustomerResponse;
import com.heuy.kt.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ap1/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("customers")
    public ResponseEntity<List<CustomerResponse>> getCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @GetMapping("customer/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("email") String email){
        return ResponseEntity.ok(customerService.getCustomer(email));
    }



}
