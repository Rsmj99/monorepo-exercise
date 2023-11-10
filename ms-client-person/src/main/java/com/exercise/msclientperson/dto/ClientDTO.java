package com.exercise.msclientperson.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.exercise.msclientperson.model.Gender;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {

	@NotBlank(message = "El campo nombre debe contener algún caracter")
	private String name;

	private Gender gender;

	@Min(0)
	private Integer age;

	private String identification;

	@NotBlank(message = "La dirección no puede estar en blanco")
	private String address;

	@NotBlank(message = "El teléfono no puede estar en blanco")
	private String phone;

	@NotBlank(message = "La contraseña no puede estar en blanco")
	private String password;

	@NotNull(message = "El estado no puede ser nulo")
	private Boolean state;
}
