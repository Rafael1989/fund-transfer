package com.hcltech.fundtransfer.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.hcltech.fundtransfer.entities.AccountType;
import com.hcltech.fundtransfer.entities.Customer;
import com.hcltech.fundtransfer.entities.Transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResumeDto {
	private String iban;
	private AccountType accountType;
	private BigDecimal balance;
	private LocalDateTime creationDate;

}
