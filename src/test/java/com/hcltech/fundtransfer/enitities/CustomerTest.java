package com.hcltech.fundtransfer.enitities;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hcltech.fundtransfer.entities.Address;
import com.hcltech.fundtransfer.entities.Customer;

@ExtendWith(MockitoExtension.class)
public class CustomerTest {

	@Test
	public void testCustomer() {
		Customer customer = Customer.builder()
			.customerId(123132l)
			.firstName("firstName")
			.lastName("lastName")
			.password("password")
			.email("email")
			.phone("phone")
			.address(new Address())
			.accounts(new ArrayList<>())
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();
		
		assertNotNull(customer);
		
		Customer customer2 = new Customer();
		customer2.setCustomerId(123132l);
		customer2.setFirstName("firstName");
		customer2.setLastName("lastName");
		customer2.setPassword("password");
		customer2.setEmail("mail");
		customer2.setPhone("phone");
		customer2.setAddress(new Address());
		customer2.setAccounts(new ArrayList<>());
		customer2.setCreatedAt(LocalDateTime.now());
		customer2.setUpdatedAt(LocalDateTime.now());
		customer2.getCustomerId();
		customer2.getFirstName();
		customer2.getLastName();
		customer2.getPassword();
		customer2.getEmail();
		customer2.getPhone();
		customer2.getAddress();
		customer2.getAccounts();
		customer2.getCreatedAt();
		customer2.getUpdatedAt();
		
		customer2.equals(customer);
		customer2.hashCode();
		customer2.toString();
	}
	
}
