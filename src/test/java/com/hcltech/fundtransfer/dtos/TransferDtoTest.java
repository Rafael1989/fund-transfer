package com.hcltech.fundtransfer.dtos;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransferDtoTest {
	
	@Test
	public void testTransferDto() {
		TransferDto transferDto = TransferDto.builder()
			.originAccount("originAccount")
			.destinationAccount("destinationAccount")
			.amount(new BigDecimal(1000))
			.comments("comments")
			.today(LocalDateTime.now())
			.build();
		
		assertNotNull(transferDto);
		
		TransferDto transferDto2 = new TransferDto();
		transferDto2.setOriginAccount("o");
		transferDto2.setDestinationAccount("d");
		transferDto2.setAmount(new BigDecimal(1000));
		transferDto2.setComments("c");
		transferDto2.setToday(LocalDateTime.now());
		transferDto2.getOriginAccount();
		transferDto2.getDestinationAccount();
		transferDto2.getAmount();
		transferDto2.getComments();
		transferDto2.getToday();
		
		transferDto2.equals(transferDto);
		transferDto2.hashCode();
		transferDto2.toString();
	}
	
}
