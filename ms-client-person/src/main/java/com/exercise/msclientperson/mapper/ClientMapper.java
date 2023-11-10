package com.exercise.msclientperson.mapper;

import org.springframework.stereotype.Component;

import com.exercise.msclientperson.dto.ClientDTO;
import com.exercise.msclientperson.model.Client;

@Component
public class ClientMapper {

	public Client mapDTOToClient(Client client, ClientDTO clientDTO) {
		client.setName(clientDTO.getName());
		client.setGender(clientDTO.getGender());
		client.setAge(clientDTO.getAge());
		client.setIdentification(clientDTO.getIdentification());
		client.setAddress(clientDTO.getAddress());
		client.setPhone(clientDTO.getPhone());
		client.setPassword(clientDTO.getPassword());
		client.setState(clientDTO.getState());
		return client;
	}

}
