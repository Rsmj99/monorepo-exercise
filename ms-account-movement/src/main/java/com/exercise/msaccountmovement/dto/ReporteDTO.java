package com.exercise.msaccountmovement.dto;

import java.util.Date;

import com.exercise.msaccountmovement.model.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDTO {

	@JsonProperty("Fecha")
	private Date date;

	@JsonIgnore
	private Long clientId;

	@JsonProperty("Cliente")
	private String client;

	@JsonProperty("Numero Cuenta")
	private String accountNumber;

	@JsonProperty("Tipo")
	private AccountType accountType;

	@JsonProperty("Saldo Inicial")
	private Double initialBalance;

	@JsonProperty("Estado")
	private Boolean state;

	@JsonProperty("Movimiento")
	private Double movement;

	@JsonProperty("Saldo Disponible")
	private Double availableBalance;
}
