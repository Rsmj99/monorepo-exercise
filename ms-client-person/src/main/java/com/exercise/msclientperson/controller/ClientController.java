package com.exercise.msclientperson.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.msclientperson.dto.ClientDTO;
import com.exercise.msclientperson.model.Client;
import com.exercise.msclientperson.service.IClientService;

@RefreshScope
@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private IClientService clientService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client save(@Valid @RequestBody ClientDTO clientDTO) {
		return clientService.createClient(clientDTO);
	}

	@GetMapping
	public List<Client> findAll() {
		return clientService.findClients();
	}

	@GetMapping("{id}")
	public Client findById(@PathVariable Long id) {
		return clientService.findClient(id);
	}

	@PutMapping("{id}")
	public Client update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable Long id) {
		return clientService.updateClient(clientDTO, id);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clientService.deleteClient(id);
	}
}
