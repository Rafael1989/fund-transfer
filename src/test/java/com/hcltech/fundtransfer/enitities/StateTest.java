package com.hcltech.fundtransfer.enitities;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hcltech.fundtransfer.entities.Country;
import com.hcltech.fundtransfer.entities.State;

@ExtendWith(MockitoExtension.class)
public class StateTest {
	
	@Test
	public void testState() {
		State state = State.builder()
			.id(123123l)
			.name("pass")
			.country(new Country())
			.build();
		
		assertNotNull(state);
		
		State state2 = new State();
		state2.setId(123123l);
		state2.setName("pass");
		state2.setCountry(new Country());
		state2.getId();
		state2.getName();
		state2.getCountry();
		
		state2.equals(state);
		state2.hashCode();
		state2.toString();
	}
	
}
