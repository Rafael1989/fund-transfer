package com.hcltech.fundtransfer.dtos;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoginUserDtoTest {
	
	@Test
	public void testLoginUserDto() {
		LoginUserDto loginUserDto = LoginUserDto.builder()
			.customerId(123123l)
			.password("pass")
			.build();
		
		assertNotNull(loginUserDto);
		
		LoginUserDto loginUserDto2 = new LoginUserDto();
		loginUserDto2.setCustomerId(123123l);
		loginUserDto2.setPassword("pass");
		loginUserDto2.getCustomerId();
		loginUserDto2.getPassword();
		
		loginUserDto2.equals(loginUserDto);
		loginUserDto2.hashCode();
		loginUserDto2.toString();
	}
	
}
