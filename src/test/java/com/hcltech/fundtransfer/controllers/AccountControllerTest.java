package com.hcltech.fundtransfer.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hcltech.fundtransfer.dtos.AccountResumeDto;
import com.hcltech.fundtransfer.dtos.TransferDto;
import com.hcltech.fundtransfer.entities.Account;
import com.hcltech.fundtransfer.entities.Transaction;
import com.hcltech.fundtransfer.repositories.TransactionRepository;
import com.hcltech.fundtransfer.services.AccountService;
import com.hcltech.fundtransfer.util.TestUtils;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {
	
	@InjectMocks
	private AccountController accountController;
	
	@Mock
	private AccountService accountService;
	
	private Authentication authentication = mock(Authentication.class);
	private SecurityContext securityContext = mock(SecurityContext.class);
	
	@Mock
	private TransactionRepository transactionRepository;
	
	@Test
	public void testFindAllAccountsResumeByCustomer() {
		List<AccountResumeDto> accountResumeDtoList = TestUtils.buildAccountResumeDtoList();
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		when(accountService.findAllAccountsResumeByCustomer(any())).thenReturn(accountResumeDtoList);
		List<AccountResumeDto> allAccountsResumeByCustomer = accountController.findAllAccountsResumeByCustomer();
		
		assertNotNull(allAccountsResumeByCustomer);
	}
	
	@Test
	public void testFindByIban() {
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		Account account = TestUtils.buildAccount();
		when(accountService.findByIbanAndCustomer(any(), any())).thenReturn(account);
		ResponseEntity<Account> accountEntity = accountController.findByIban(account.getIban());
		
		assertNotNull(accountEntity);
	}
	
	@Test
	public void testFindAllTransactionsByIban() {
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		Account account = TestUtils.buildAccount();
		when(accountService.findByIbanAndCustomer(any(), any())).thenReturn(account);
		Pageable pageable = PageRequest.of(0, 10);
		ResponseEntity<Page<Transaction>> allTransactionsByIban = accountController.findAllTransactionsByIban(pageable, account.getIban());
		
		assertNotNull(allTransactionsByIban);
	}
	
	@Test
	public void testFindByIbanAndTransactionId() {
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		Account account = TestUtils.buildAccount();
		when(accountService.findByIbanAndCustomer(any(), any())).thenReturn(account);
		ResponseEntity<Transaction> byIbanAndTransactionId = accountController.findByIbanAndTransactionId(account.getIban(),1l);
		
		assertNotNull(byIbanAndTransactionId);
	}
	
	@Test
	public void testCreate() {
		Account account = TestUtils.buildAccount("SADASDDS123123124");
		Optional<Account> optional = java.util.Optional.empty();
		when(accountService.findById(any())).thenReturn(optional);
		when(accountService.save(any())).thenReturn(account);
		ResponseEntity<Account> responseEntity = accountController.create(account);
		
		assertNotNull(responseEntity);
	}
	
	@Test
	public void testTransfer() {
		TransferDto transferDto = TestUtils.buildTransferDto();
		when(accountService.transfer(any())).thenReturn(transferDto);
		TransferDto transfer = accountController.transfer(transferDto);
		
		assertNotNull(transfer);
	}
	
}
