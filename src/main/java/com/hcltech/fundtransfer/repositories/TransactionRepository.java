package com.hcltech.fundtransfer.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcltech.fundtransfer.entities.Account;
import com.hcltech.fundtransfer.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	Page<Transaction> findByAccount(Account account, Pageable pageable);

}
