package com.exercise.msaccountmovement.controller;

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

import com.exercise.msaccountmovement.dto.MovementDTO;
import com.exercise.msaccountmovement.model.Movement;
import com.exercise.msaccountmovement.service.IMovementService;

@RefreshScope
@RestController
@RequestMapping("/movement")
public class MovementController {

	@Autowired
	private IMovementService movementService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Movement save(@Valid @RequestBody MovementDTO movementDto) {
		return movementService.saveMovement(movementDto);
	}

	@GetMapping("{id}")
	public Movement findById(@PathVariable Long id) {
		return movementService.findMovement(id);
	}

	@GetMapping()
	public List<Movement> findAll() {
		return movementService.findMovements();
	}

	@PutMapping("{id}")
	public Movement update(@Valid @RequestBody MovementDTO movementDto, @PathVariable Long id) {
		return movementService.updateMovement(movementDto, id);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		movementService.deleteMovement(id);
	}
}
