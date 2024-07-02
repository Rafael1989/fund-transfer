package com.hcltech.fundtransfer.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcltech.fundtransfer.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByCustomerId(Long customerId);
}
