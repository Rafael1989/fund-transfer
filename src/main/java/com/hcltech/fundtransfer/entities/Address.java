package com.hcltech.fundtransfer.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
