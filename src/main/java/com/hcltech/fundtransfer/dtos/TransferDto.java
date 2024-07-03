package com.hcltech.fundtransfer.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferDto {
	@NotBlank
    private String originAccount;
	@NotBlank
    private String destinationAccount;
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amount;
    private String comments;
    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime today;
}
