package com.hcltech.fundtransfer.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hcltech.fundtransfer.entities.Customer;
import com.hcltech.fundtransfer.repositories.CustomerRepository;
import com.hcltech.fundtransfer.util.TestUtils;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	
	@InjectMocks
	private CustomerService customerService;
	
	@Mock
	private CustomerRepository customerRepository;
	
	
	@Test
	public void testAllCustomers() {
		List<Customer> customers = TestUtils.buildCustomerList();
		    
		when(customerRepository.findAll()).thenReturn(customers);
		List<Customer> allCustomers = customerService.allCustomers();
		
		assertNotNull(allCustomers);
	}
	
}
