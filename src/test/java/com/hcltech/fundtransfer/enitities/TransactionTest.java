package com.hcltech.fundtransfer.enitities;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hcltech.fundtransfer.entities.Account;
import com.hcltech.fundtransfer.entities.Transaction;
import com.hcltech.fundtransfer.entities.TransactionCategory;
import com.hcltech.fundtransfer.entities.TransactionType;

@ExtendWith(MockitoExtension.class)
public class TransactionTest {
	
	@Test
	public void testTransaction() {
		Transaction transaction = Transaction.builder()
			.id(123123l)
			.description("description")
			.category(TransactionCategory.TRANSFER)
			.type(TransactionType.CREDIT)
			.amount(new BigDecimal(1000))
			.valuationDate(LocalDateTime.now())
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.account(new Account())
			.build();
		
		assertNotNull(transaction);
		
		Transaction transaction2 = new Transaction();
		transaction2.setId(123123l);
		transaction2.setDescription("description");
		transaction2.setCategory(TransactionCategory.TRANSFER);
		transaction2.setType(TransactionType.CREDIT);
		transaction2.setAmount(new BigDecimal(1000));
		transaction2.setValuationDate(LocalDateTime.now());
		transaction2.setCreatedAt(LocalDateTime.now());
		transaction2.setUpdatedAt(LocalDateTime.now());
		transaction2.setAccount(new Account());
		transaction2.getId();
		transaction2.getDescription();
		transaction2.getCategory();
		transaction2.getType();
		transaction2.getAmount();
		transaction2.getValuationDate();
		transaction2.getCreatedAt();
		transaction2.getUpdatedAt();
		transaction2.getAccount();
		
		transaction2.equals(transaction);
		transaction2.hashCode();
		transaction2.toString();
	}
	
}
