package com.hcltech.fundtransfer.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hcltech.fundtransfer.dtos.LoginUserDto;
import com.hcltech.fundtransfer.dtos.RegisterUserDto;
import com.hcltech.fundtransfer.entities.Customer;
import com.hcltech.fundtransfer.repositories.CustomerRepository;
import com.hcltech.fundtransfer.util.TestUtils;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {
	
	@InjectMocks
	private AuthenticationService authenticationService;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private AuthenticationManager authenticationManager;
	
	private Authentication authentication = mock(Authentication.class);
	
	@Test
	public void testSignup() {
		Customer customer = TestUtils.buildCustomer();
		RegisterUserDto registerUserDto = TestUtils.buildRegisterUserDto();
		Optional<Customer> optional = java.util.Optional.empty();
		when(customerRepository.findByCustomerId(anyLong())).thenReturn(optional);
		when(passwordEncoder.encode(any())).thenReturn("pass");
		when(customerRepository.save(any())).thenReturn(customer);
		Customer signup = authenticationService.signup(registerUserDto);
		
		assertNotNull(signup);
	}
	
	@Test
	public void testAuthenticate() {
		LoginUserDto loginUserDto = TestUtils.buildLoginUserDto();
		Customer customer = TestUtils.buildCustomer();
		Optional<Customer> optional = java.util.Optional.empty();
		when(customerRepository.findByCustomerId(anyLong())).thenReturn(optional.of(customer));
		when(authenticationManager.authenticate(any())).thenReturn(authentication);
		Customer signup = authenticationService.authenticate(loginUserDto);
		
		assertNotNull(signup);
	}
	
}
