package com.hcltech.fundtransfer.services;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hcltech.fundtransfer.dtos.LoginUserDto;
import com.hcltech.fundtransfer.dtos.RegisterUserDto;
import com.hcltech.fundtransfer.entities.Customer;
import com.hcltech.fundtransfer.exceptions.UserAlreadyExistException;
import com.hcltech.fundtransfer.repositories.CustomerRepository;

@Service
public class AuthenticationService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        CustomerRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.customerRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer signup(RegisterUserDto input) {
    	Optional<Customer> customerOptional = customerRepository.findByCustomerId(input.getCustomerId());
    	if(customerOptional.isPresent()) {
    		throw new UserAlreadyExistException("The user already exist.");
    	}
        
        var customer = Customer.builder()
    		.customerId(input.getCustomerId())
    		.password(passwordEncoder.encode(input.getPassword()))
    		.firstName(input.getFirstName())
    		.lastName(input.getLastName())
    		.email(input.getEmail())
    		.phone(input.getPhone())
    		.address(input.getAddress()).build();

        return customerRepository.save(customer);
    }

    public Customer authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.getCustomerId(),
                input.getPassword()
            )
        );

        return customerRepository.findByCustomerId(input.getCustomerId()).orElseThrow();
    }

}
