package com.hcltech.fundtransfer.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@NotBlank
	private String postalCode;
	
	@NotBlank
	private String street;
	
	@NotBlank
	private String number;
	
	@NotBlank
	private String complement;
	
	@NotBlank
	private String neighborhood;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "city_id")
	private City city;
	
}
