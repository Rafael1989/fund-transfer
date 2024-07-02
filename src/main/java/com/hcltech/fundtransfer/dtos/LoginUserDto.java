package com.hcltech.fundtransfer.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginUserDto {
	
	@NotNull
    private Long customerId;
	@NotBlank
    private String password;

}
