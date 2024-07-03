package com.hcltech.fundtransfer.enitities;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hcltech.fundtransfer.entities.Country;

@ExtendWith(MockitoExtension.class)
public class CountryTest {
	
	@Test
	public void testCountry() {
		Country country = Country.builder()
			.id(123123l)
			.name("pass")
			.build();
		
		assertNotNull(country);
		
		Country country2 = new Country();
		country2.setId(123123l);
		country2.setName("pass");
		country2.getId();
		country2.getName();
		
		country2.equals(country);
		country2.hashCode();
		country2.toString();
	}
	
}
