package com.hcltech.fundtransfer.entities;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	@EqualsAndHashCode.Include
	@Id
	private String iban;
	
	@NotNull
    @Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AccountType accountType;
	
	@NotBlank
	@Column(nullable = false)
	private String description;
	
	@NotBlank
	@Column(nullable = false)
	private String name;
	
	@NotBlank
	@Column(nullable = false)
	private String holder;
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal balance;
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal limitAccount;
	
	@NotBlank
	@Column(nullable = false)
	private String bic;
	
	@CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @ManyToOne
	@JoinColumn(nullable = false)
	private Customer customer;
    
    @JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	private List<Transaction> transactions = new ArrayList<>();
	
}