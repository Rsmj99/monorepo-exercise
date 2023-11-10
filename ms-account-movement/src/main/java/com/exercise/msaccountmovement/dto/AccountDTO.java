package com.exercise.msaccountmovement.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.exercise.msaccountmovement.model.AccountType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

	@NotBlank(message = "El número de cuenta no puede estar en blanco")
	private String accountNumber;

	@NotNull(message = "El tipo de cuenta no puede ser nulo")
	private AccountType accountType;

	@NotNull(message = "El saldo inicial no puede ser nulo")
	@Min(0)
	private Double initialBalance;

	@NotNull(message = "El estado no puede ser nulo")
	private Boolean state;

	@NotNull(message = "El id cliente no puede ser nulo")
	private Long clientId;
}