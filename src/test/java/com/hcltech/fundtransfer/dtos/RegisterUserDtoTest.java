package com.hcltech.fundtransfer.dtos;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hcltech.fundtransfer.entities.Address;

@ExtendWith(MockitoExtension.class)
public class RegisterUserDtoTest {
	
	@Test
	public void testRegisterUserDto() {
		RegisterUserDto registerUserDto = RegisterUserDto.builder()
			.customerId(123123l)
			.password("pass")
			.firstName("firstName")
			.lastName("lastName")
			.email("email")
			.phone("phone")
			.address(new Address())
			.build();
		
		assertNotNull(registerUserDto);
		
		RegisterUserDto registerUserDto2 = new RegisterUserDto();
		registerUserDto2.setCustomerId(123123l);
		registerUserDto2.setPassword("pass");
		registerUserDto2.setFirstName("firstName");
		registerUserDto2.setLastName("lastName");
		registerUserDto2.setEmail("mail");
		registerUserDto2.setPhone("phone");
		registerUserDto2.setAddress(new Address());
		registerUserDto2.getCustomerId();
		registerUserDto2.getPassword();
		registerUserDto2.getFirstName();
		registerUserDto2.getLastName();
		registerUserDto2.getEmail();
		registerUserDto2.getPhone();
		registerUserDto2.getAddress();
		
		registerUserDto2.equals(registerUserDto);
		registerUserDto2.hashCode();
		registerUserDto2.toString();
	}
	
}
