package com.hcltech.fundtransfer.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.hcltech.fundtransfer.dtos.LoginUserDto;
import com.hcltech.fundtransfer.dtos.RegisterUserDto;
import com.hcltech.fundtransfer.entities.Customer;
import com.hcltech.fundtransfer.services.AuthenticationService;
import com.hcltech.fundtransfer.services.JwtService;
import com.hcltech.fundtransfer.util.TestUtils;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {
	
	@InjectMocks
	private AuthenticationController authenticationController;
	
	@Mock
	private JwtService jwtService;
	
	@Mock
	private AuthenticationService authenticationService;
	
	@Test
	public void testRegister() {
		Customer customer = TestUtils.buildCustomer();
		RegisterUserDto registerUserDto = TestUtils.buildRegisterUserDto();
		when(authenticationService.signup(any())).thenReturn(customer);
		ResponseEntity<?> register = authenticationController.register(registerUserDto);
		
		assertNotNull(register);
	}
	
	@Test
	public void testAuthenticate() {
		Customer customer = TestUtils.buildCustomer();
		LoginUserDto loginUserDto = TestUtils.buildLoginUserDto();
		when(authenticationService.authenticate(any())).thenReturn(customer);
		when(jwtService.generateToken(customer)).thenReturn("token");
		ResponseEntity<?> register = authenticationController.authenticate(loginUserDto);
		
		assertNotNull(register);
	}
	
}
