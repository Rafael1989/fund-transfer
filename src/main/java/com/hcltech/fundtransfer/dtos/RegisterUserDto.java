package com.hcltech.fundtransfer.dtos;


import com.hcltech.fundtransfer.entities.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserDto {
	@NotNull
    private Long customerId;
	@NotBlank
    private String password;
	@NotBlank
    private String firstName;
	@NotBlank
    private String lastName;
	@NotBlank
    private String email;
	@NotBlank
    private String phone;
	@NotNull
	private Address address;

}
