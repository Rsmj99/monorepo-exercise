package com.exercise.msaccountmovement.service;

import java.util.List;

import com.exercise.msaccountmovement.dto.MovementDTO;
import com.exercise.msaccountmovement.model.Movement;

public interface IMovementService {

	Movement saveMovement(MovementDTO movementDto);

	Movement updateMovement(MovementDTO movementDto, Long movementId);

	List<Movement> findMovements();

	void deleteMovement(Long movementId);

	Movement findMovement(Long movementId);
}
