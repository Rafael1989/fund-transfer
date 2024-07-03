package com.hcltech.fundtransfer.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hcltech.fundtransfer.services.CustomerService;
import com.hcltech.fundtransfer.util.TestUtils;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
	
	@InjectMocks
	private CustomerController customerController;
	
	@Mock
	private CustomerService customerService;
	
	private Authentication authentication = mock(Authentication.class);
	private SecurityContext securityContext = mock(SecurityContext.class);
	
	@Test
	public void testRegister() {
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		ResponseEntity<?> register = customerController.authenticatedUser();
		
		assertNotNull(register);
	}
	
	@Test
	public void testAllCustomers() {
		when(customerService.allCustomers()).thenReturn(TestUtils.buildCustomerList());
		ResponseEntity<?> register = customerController.allCustomers();
		
		assertNotNull(register);
	}
	
}
