package com.hcltech.fundtransfer.enitities;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hcltech.fundtransfer.entities.City;
import com.hcltech.fundtransfer.entities.State;

@ExtendWith(MockitoExtension.class)
public class CityTest {
	
	@Test
	public void testCity() {
		City city = City.builder()
			.id(123123l)
			.name("pass")
			.state(new State())
			.build();
		
		assertNotNull(city);
		
		City city2 = new City();
		city2.setId(123123l);
		city2.setName("pass");
		city2.setState(new State());
		city2.getId();
		city2.getName();
		city2.getState();
		
		city2.equals(city);
		city2.hashCode();
		city2.toString();
	}
	
}
