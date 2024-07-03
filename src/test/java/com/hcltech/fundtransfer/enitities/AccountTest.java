package com.hcltech.fundtransfer.enitities;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hcltech.fundtransfer.entities.Account;
import com.hcltech.fundtransfer.entities.AccountType;
import com.hcltech.fundtransfer.entities.Customer;

@ExtendWith(MockitoExtension.class)
public class AccountTest {

	@Test
	public void testAccount() {
		Account account = Account.builder()
			.iban("iban")
			.accountType(AccountType.CURRENT)
			.description("description")
			.name("name")
			.holder("holder")
			.balance(new BigDecimal(1000))
			.limitAccount(new BigDecimal(1000))
			.bic("bic")
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.customer(new Customer())
			.transactions(new ArrayList<>())
			.build();
		
		assertNotNull(account);
		
		Account account2 = new Account();
		account2.setIban("iban");
		account2.setAccountType(AccountType.CURRENT);
		account2.setDescription("description");
		account2.setName("name");
		account2.setHolder("holder");
		account2.setBalance(new BigDecimal(1000));
		account2.setLimitAccount(new BigDecimal(1000));
		account2.setBic("bic");
		account2.setCreatedAt(LocalDateTime.now());
		account2.setUpdatedAt(LocalDateTime.now());
		account2.setCustomer(new Customer());
		account2.setTransactions(new ArrayList<>());
		account2.getIban();
		account2.getAccountType();
		account2.getDescription();
		account2.getName();
		account2.getHolder();
		account2.getHolder();
		account2.getBalance();
		account2.getLimitAccount();
		account2.getBic();
		account2.getCreatedAt();
		account2.getUpdatedAt();
		account2.getCustomer();
		account2.getTransactions();
		
		account2.equals(account);
		account2.hashCode();
		account2.toString();
	}
	
}
