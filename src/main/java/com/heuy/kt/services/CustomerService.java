package com.heuy.kt.services;

import com.heuy.kt.dto.CustomerResponse;
import com.heuy.kt.exception.NotFoundException;
import com.heuy.kt.models.Customer;
import com.heuy.kt.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerResponse getCustomer(String email){
        Customer customer = customerRepo.findByEmail(email)
                .orElseThrow(
                        () -> new NotFoundException("Structure not recognised", 404)
                );
        return CustomerResponse
                .builder()
                .name(customer.getName())
                .build();
    }

    public List<CustomerResponse> getAllCustomer(){
        return customerRepo
                .findAll()
                .stream()
                .map(customer -> CustomerResponse.builder().name(customer.getName()).build())
                .toList();
    }


}
