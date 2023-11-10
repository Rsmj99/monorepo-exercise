package com.exercise.msclientperson.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.exercise.msclientperson.dto.ClientDTO;
import com.exercise.msclientperson.exception.ClientNotFoundException;
import com.exercise.msclientperson.mapper.ClientMapper;
import com.exercise.msclientperson.model.Client;
import com.exercise.msclientperson.repository.ClientRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

	@Mock
	private ClientRepository clientRepository;

	@Mock
	private ClientMapper clientMapper;

	@InjectMocks
	private ClientServiceImpl clientService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createClient() {
		// Arrange
		ClientDTO clientDTO = new ClientDTO();
		Client mappedClient = new Client();
		when(clientMapper.mapDTOToClient(any(Client.class), eq(clientDTO))).thenReturn(mappedClient);
		when(clientRepository.save(mappedClient)).thenReturn(mappedClient);

		// Act
		Client result = clientService.createClient(clientDTO);

		// Assert
		assertEquals(mappedClient, result);
		verify(clientMapper, times(1)).mapDTOToClient(any(Client.class), eq(clientDTO));
		verify(clientRepository, times(1)).save(mappedClient);
	}

	@Test
	public void findClients() {
		// Arrange
		List<Client> clients = new ArrayList<>();
		when(clientRepository.findAll()).thenReturn(clients);

		// Act
		List<Client> result = clientService.findClients();

		// Assert
		assertEquals(clients, result);
		verify(clientRepository, times(1)).findAll();
	}

	@Test
	public void deleteClient() {
		// Arrange
		Long clientId = 1L;

		// Act
		clientService.deleteClient(clientId);

		// Assert
		verify(clientRepository, times(1)).deleteById(clientId);
	}

	@Test
	public void findClient_existingClient() {
		// Arrange
		Long clientId = 1L;
		Client client = new Client();
		when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

		// Act
		Client result = clientService.findClient(clientId);

		// Assert
		assertEquals(client, result);
	}

	@Test
	public void findClient_nonExistingClient() {
		// Arrange
		Long clientId = 1L;
		when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

		// Act and Assert
		assertThrows(ClientNotFoundException.class, () -> clientService.findClient(clientId));
	}
}
