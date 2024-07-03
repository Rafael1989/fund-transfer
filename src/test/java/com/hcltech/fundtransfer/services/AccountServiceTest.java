package com.hcltech.fundtransfer.services;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hcltech.fundtransfer.dtos.AccountResumeDto;
import com.hcltech.fundtransfer.dtos.TransferDto;
import com.hcltech.fundtransfer.entities.Account;
import com.hcltech.fundtransfer.repositories.AccountRepository;
import com.hcltech.fundtransfer.repositories.TransactionRepository;
import com.hcltech.fundtransfer.util.TestUtils;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
	
	@InjectMocks
	private AccountService accountService;
	
	@Mock
	private AccountRepository accountRepository;
	
	@Mock
	private TransactionRepository transactionRepository;
	
	private Authentication authentication = mock(Authentication.class);
	private SecurityContext securityContext = mock(SecurityContext.class);
	
	@Test
	public void testFindAllAccountsResumeByCustomer() {
		List<Account> accounts = TestUtils.buildAccounts();
		    
		when(accountRepository.findByCustomer(any())).thenReturn(accounts);
		List<AccountResumeDto> allAccountsResumeByCustomer = accountService.findAllAccountsResumeByCustomer(TestUtils.buildCustomer());
		
		assertNotNull(allAccountsResumeByCustomer);
	}
	
	@Test
	public void testSave() {
		when(accountRepository.save(any())).thenReturn(TestUtils.buildAccount());
		Account account = accountService.save(TestUtils.buildAccount());
		
		assertNotNull(account);
	}
	
	@Test
	public void testFindByIbanAndCustomer() {
		when(accountRepository.findByIbanAndCustomer(any(), any())).thenReturn(TestUtils.buildAccount());
		Account account = accountService.findByIbanAndCustomer(TestUtils.buildAccount().getIban(), TestUtils.buildCustomer());
		
		assertNotNull(account);
	}
	
	@Test
	public void testTransfer() {
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		when(accountRepository.findByIbanAndCustomer(any(), any())).thenReturn(TestUtils.buildAccount());
		when(accountRepository.findByIban(any())).thenReturn(TestUtils.buildAccount());
		when(accountRepository.save(any())).thenReturn(TestUtils.buildAccount());
		when(transactionRepository.save(any())).thenReturn(TestUtils.buildTransaction());
		TransferDto transfer = accountService.transfer(TestUtils.buildTransferDto());
		
		assertNotNull(transfer);
	}
	
	@Test
	public void testFindById() {
		when(accountRepository.findById(any())).thenReturn(Optional.of(TestUtils.buildAccount()));
		Optional<Account> account = accountService.findById(TestUtils.buildAccount());
		
		assertNotNull(account);
	}
	

}
