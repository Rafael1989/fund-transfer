package com.hcltech.fundtransfer.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hcltech.fundtransfer.entities.Customer;
import com.hcltech.fundtransfer.repositories.CustomerRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> allCustomers() {
        List<Customer> customers = new ArrayList<>();

        customerRepository.findAll().forEach(customers::add);

        return customers;
    }
}
