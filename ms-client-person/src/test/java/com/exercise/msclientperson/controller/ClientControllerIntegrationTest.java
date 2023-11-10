package com.exercise.msclientperson.controller;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.exercise.msclientperson.dto.ClientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static Long clientId;

	@Test
	public void test1SaveClient() throws Exception {
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setName("Roger Muguruza");
		clientDTO.setAge(23);
		clientDTO.setAddress("La Unión");
		clientDTO.setPhone("926648345");
		clientDTO.setPassword("12345");
		clientDTO.setState(true);

		mockMvc.perform(post("/client").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(clientDTO))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.phone").value("926648345"));

		clientId = jdbcTemplate.queryForObject("SELECT MAX(id) FROM clients", Long.class);
	}

	@Test
	public void test2FindClientById() throws Exception {
		mockMvc.perform(get("/client/{id}", clientId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Roger Muguruza"));
	}

	@Test
	public void test3UpdateClient() throws Exception {
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setName("Roger Muguruza Julca");
		clientDTO.setAge(24);
		clientDTO.setAddress("Calle La Unión");
		clientDTO.setPhone("926648345");
		clientDTO.setPassword("12345");
		clientDTO.setState(true);

		mockMvc.perform(put("/client/{id}", clientId).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(clientDTO))).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Roger Muguruza Julca")).andExpect(jsonPath("$.age").value(24));
	}

	@Test
	public void test4FindAllClients() throws Exception {
		mockMvc.perform(get("/client")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(greaterThan(0))));
	}

	@Test
	public void test5DeleteClient() throws Exception {
		mockMvc.perform(delete("/client/{id}", clientId)).andExpect(status().isNoContent());
	}
}