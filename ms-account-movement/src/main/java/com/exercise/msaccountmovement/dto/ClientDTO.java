package com.exercise.msaccountmovement.dto;

import com.exercise.msaccountmovement.model.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

	private Long id;

	private String name;

	private Gender gender;

	private Integer age;

	private String identification;

	private String address;

	private String phone;

	private String password;

	private Boolean state;
}
