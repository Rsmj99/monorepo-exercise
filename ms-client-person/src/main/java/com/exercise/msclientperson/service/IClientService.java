package com.exercise.msclientperson.service;

import java.util.List;

import com.exercise.msclientperson.dto.ClientDTO;
import com.exercise.msclientperson.model.Client;

public interface IClientService {

	Client createClient(ClientDTO clientDTO);

	Client updateClient(ClientDTO clientDTO, Long id);

	List<Client> findClients();

	void deleteClient(Long clientId);

	Client findClient(Long clientId);
}
