package com.hcltech.fundtransfer.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcltech.fundtransfer.entities.Customer;
import com.hcltech.fundtransfer.services.CustomerService;

@RequestMapping("/customers")
@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/me")
    public ResponseEntity<Customer> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Customer currentCustomer = (Customer) authentication.getPrincipal();

        return ResponseEntity.ok(currentCustomer);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> allCustomers() {
        List <Customer> customers = customerService.allCustomers();

        return ResponseEntity.ok(customers);
    }
}
