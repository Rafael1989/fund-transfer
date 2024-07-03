package com.hcltech.fundtransfer.enitities;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hcltech.fundtransfer.entities.Address;
import com.hcltech.fundtransfer.entities.City;

@ExtendWith(MockitoExtension.class)
public class AddressTest {
	
	@Test
	public void testAddress() {
		Address address = Address.builder()
			.postalCode("postalCode")
			.street("street")
			.number("number")
			.complement("complement")
			.neighborhood("neighborhood")
			.city(new City())
			.build();
		
		assertNotNull(address);
		
		Address address2 = new Address();
		address2.setPostalCode("postalCode");
		address2.setStreet("street");
		address2.setNumber("number");
		address2.setComplement("complement");
		address2.setNeighborhood("neighborhood");
		address2.setCity(new City());
		address2.getPostalCode();
		address2.getStreet();
		address2.getNumber();
		address2.getComplement();
		address2.getNeighborhood();
		address2.getCity();
		
		address2.equals(address);
		address2.hashCode();
		address2.toString();
	}
	
}
