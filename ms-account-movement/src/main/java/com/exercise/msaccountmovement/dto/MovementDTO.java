package com.exercise.msaccountmovement.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.exercise.msaccountmovement.model.MovementType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovementDTO {

	@NotNull(message = "El tipo de movimiento no puede ser nulo")
	private MovementType movementType;

	@NotNull(message = "El valor no puede ser nulo")
	@Min(1)
	private Double value;

	@NotNull(message = "El id cuenta no puede ser nulo")
	private Long accountId;
}
