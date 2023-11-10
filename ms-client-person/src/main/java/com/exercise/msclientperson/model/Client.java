package com.exercise.msclientperson.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String password;

	private Boolean state;
}
