package com.hcltech.fundtransfer.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcltech.fundtransfer.dtos.AccountResumeDto;
import com.hcltech.fundtransfer.dtos.TransferDto;
import com.hcltech.fundtransfer.entities.Account;
import com.hcltech.fundtransfer.entities.Customer;
import com.hcltech.fundtransfer.entities.Transaction;
import com.hcltech.fundtransfer.exceptions.UserAlreadyExistException;
import com.hcltech.fundtransfer.repositories.TransactionRepository;
import com.hcltech.fundtransfer.services.AccountService;

import jakarta.validation.Valid;

@RequestMapping("/accounts")
@RestController
public class AccountController {
    private final AccountService accountService;
    private final TransactionRepository transactionRepository;

    public AccountController(AccountService accountService, TransactionRepository transactionRepository) {
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/resume")
    public List<AccountResumeDto> findAllAccountsResumeByCustomer() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Customer currentCustomer = (Customer) authentication.getPrincipal();
        return accountService.findAllAccountsResumeByCustomer(currentCustomer);
    }
    
    @GetMapping("/{iban}")
    public ResponseEntity<Account> findByIban(@PathVariable String iban) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Customer currentCustomer = (Customer) authentication.getPrincipal();
        Account account = accountService.findByIbanAndCustomer(iban, currentCustomer);
        
        if (account != null) {
			return ResponseEntity.ok(account);
		}
		
		return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{iban}/transactions")
	public ResponseEntity<Page<Transaction>> findAllTransactionsByIban(@PageableDefault(size = 10) Pageable pageable, @PathVariable String iban) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Customer currentCustomer = (Customer) authentication.getPrincipal();
        Account account = accountService.findByIbanAndCustomer(iban, currentCustomer);
        if (account == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(transactionRepository.findByAccount(account, pageable));
	}
    
    @GetMapping("/{iban}/transaction/{id}")
    public ResponseEntity<Transaction> findByIbanAndTransactionId(@PathVariable String iban, @PathVariable Long id) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Customer currentCustomer = (Customer) authentication.getPrincipal();
        Account account = accountService.findByIbanAndCustomer(iban, currentCustomer);
        if (account == null) {
			return ResponseEntity.notFound().build();
		}
        for(Transaction transaction : account.getTransactions()) {
        	if(transaction.getId().equals(id)) {
        		return ResponseEntity.ok(transaction);
        	}
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<Account> create(@RequestBody @Valid Account account) {
    	Optional<Account> accountOptional = accountService.findById(account);
    	if(accountOptional.isPresent()) {
    		throw new UserAlreadyExistException("The account already exist.");
    	}
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.save(account));
    }
    
    @PatchMapping("/transfer")
    public TransferDto transfer(@RequestBody @Valid TransferDto transferDto) {
    	return accountService.transfer(transferDto);
    }

}
