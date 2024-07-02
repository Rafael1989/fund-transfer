package com.hcltech.fundtransfer.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hcltech.fundtransfer.dtos.RegisterUserDto;
import com.hcltech.fundtransfer.dtos.TransferDto;
import com.hcltech.fundtransfer.entities.Account;
import com.hcltech.fundtransfer.entities.AccountType;
import com.hcltech.fundtransfer.entities.Address;
import com.hcltech.fundtransfer.entities.City;
import com.hcltech.fundtransfer.entities.Country;
import com.hcltech.fundtransfer.entities.Customer;
import com.hcltech.fundtransfer.entities.State;
import com.hcltech.fundtransfer.entities.Transaction;
import com.hcltech.fundtransfer.entities.TransactionCategory;
import com.hcltech.fundtransfer.entities.TransactionType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TestUtils {
	
	public static Account buildAccount() {
		return Account.builder()
		.iban("SADASDDS123123123")
	    .accountType(AccountType.CURRENT)
	    .description("Description")
	    .name("Name")
	    .holder("Holder")
	    .balance(new BigDecimal(1000))
	    .limitAccount(new BigDecimal(100))
	    .bic("BIC")
	    .createdAt(LocalDateTime.now())
	    .updatedAt(LocalDateTime.now())
	    .customer(buildCustomer())
	    .transactions(buildTransaction()).build();
	}
	
	public static TransferDto buildTransferDto() {
		return TransferDto.builder()
		.originAccount("originAccount")
		.destinationAccount("destinationAccount")
		.amount(new BigDecimal(1000))
		.comments("comments")
		.today(LocalDateTime.now()).build();
	}
	
	public static List<Transaction> buildTransaction(){
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(Transaction.builder()
				.id(1231323L)
				.description("description")
				.category(TransactionCategory.TRANSFER)
				.type(TransactionType.CREDIT)
				.amount(new BigDecimal(1000))
				.valuationDate(LocalDateTime.now())
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.account(null).build());
		return transactions;
				
	}
	
	public static List<Account> buildAccounts() {
		List<Account> accounts = new ArrayList<>();
		accounts.add(buildAccount());
		return accounts;
	}

	public static Customer buildCustomer() {
		return Customer.builder()
				.customerId(12312321L)
				.firstName("FirstName")
				.lastName("lastName")
				.password("password")
				.email("email")
				.phone("phone")
				.address(buildAddress())
				.accounts(null)
				.createdAt(LocalDateTime.now()).build();
	}
	
	public static Customer buildCustomer(Long customerId) {
		return Customer.builder()
				.customerId(customerId)
				.firstName("FirstName")
				.lastName("lastName")
				.password("password")
				.email("email")
				.phone("phone")
				.address(buildAddress())
				.accounts(null)
				.createdAt(LocalDateTime.now()).build();
	}
	
	public static RegisterUserDto buildRegisterUserDto() {
		return RegisterUserDto.builder()
				.customerId(12312321L)
				.firstName("FirstName")
				.lastName("lastName")
				.password("password")
				.email("email")
				.phone("phone")
				.address(buildAddress()).build();
	}
	
	public static RegisterUserDto buildRegisterUserDto(Long customerId) {
		return RegisterUserDto.builder()
				.customerId(customerId)
				.firstName("FirstName")
				.lastName("lastName")
				.password("password")
				.email("email")
				.phone("phone")
				.address(buildAddress()).build();
	}
	
	public static Address buildAddress() {
		return Address.builder()
				.postalCode("postalCode")
				.street("street")
				.number("number")
				.complement("complement")
				.neighborhood("neighborhood")
				.city(buildCity()).build();
				
	}
	
	public static City buildCity() {
		return City.builder()
				.id(123123L)
				.name("name")
				.state(buildState()).build();
				
	}
	
	public static State buildState() {
		return State.builder()
				.id(12312312L)
				.name("name")
				.country(buildCountry()).build();
				
	}
	
	public static Country buildCountry() {
		return Country.builder()
				.id(12312312L)
				.name("Country").build();
				
	}

}
