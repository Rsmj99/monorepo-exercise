package com.exercise.msclientperson.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.exercise.msclientperson.dto.ClientDTO;
import com.exercise.msclientperson.exception.ClientNotFoundException;
import com.exercise.msclientperson.mapper.ClientMapper;
import com.exercise.msclientperson.model.Client;
import com.exercise.msclientperson.repository.ClientRepository;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ClientMapper clientMapper;

	@Override
	public Client createClient(ClientDTO clientDTO) {
		Client client = clientMapper.mapDTOToClient(new Client(), clientDTO);
		return clientRepository.save(client);
	}

	@Override
	@Cacheable("findClients")
	public List<Client> findClients() {
		return clientRepository.findAll();
	}

	@Override
	public void deleteClient(Long clientId) {
		clientRepository.deleteById(clientId);
	}

	@Override
	@Cacheable("findClient")
	public Client findClient(Long clientId) {
		return clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
	}

	@Override
	public Client updateClient(ClientDTO clientDTO, Long clientId) {
		Client client = clientMapper.mapDTOToClient(this.findClient(clientId), clientDTO);
		return clientRepository.save(client);
	}
}
