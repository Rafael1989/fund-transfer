package com.hcltech.fundtransfer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcltech.fundtransfer.entities.Account;
import com.hcltech.fundtransfer.entities.Customer;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	List<Account> findByCustomer(Customer customer);

	Account findByIbanAndCustomer(String iban, Customer customer);

	Account findByIban(String iban);
}
