package com.hcltech.fundtransfer.dtos;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hcltech.fundtransfer.entities.AccountType;

@ExtendWith(MockitoExtension.class)
public class AccountResumeDtoTest {
	
	@Test
	public void testAccountResumeDto() {
		AccountResumeDto accountResumeDto = AccountResumeDto.builder()
			.iban("iban")
			.accountType(AccountType.CURRENT)
			.balance(new BigDecimal(1000))
			.creationDate(LocalDateTime.now())
			.build();
		
		assertNotNull(accountResumeDto);
		
		AccountResumeDto accountResumeDto2 = new AccountResumeDto();
		accountResumeDto2.setAccountType(AccountType.CURRENT);
		accountResumeDto2.setIban("iban");
		accountResumeDto2.setBalance(new BigDecimal(1000));
		accountResumeDto2.setCreationDate(LocalDateTime.now());
		accountResumeDto2.getAccountType();
		accountResumeDto2.getIban();
		accountResumeDto2.getBalance();
		accountResumeDto2.getCreationDate();
		
		accountResumeDto.equals(accountResumeDto2);
		accountResumeDto.hashCode();
		accountResumeDto.toString();
	}
	
}
