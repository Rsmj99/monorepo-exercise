package com.exercise.msaccountmovement.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.exercise.msaccountmovement.dto.ClientDTO;

@FeignClient(name = "ms-client-person", url = "localhost:8090/api/ms-cp/client")
public interface ClientPersonRest {

	@GetMapping("{id}")
	ClientDTO findById(@PathVariable Long id);
}
