package com.exercise.msaccountmovement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.exercise.msaccountmovement.dto.MovementDTO;
import com.exercise.msaccountmovement.exception.InsufficientBalanceException;
import com.exercise.msaccountmovement.exception.MovementNotFoundException;
import com.exercise.msaccountmovement.model.Account;
import com.exercise.msaccountmovement.model.Movement;
import com.exercise.msaccountmovement.model.MovementType;
import com.exercise.msaccountmovement.repository.AccountRepository;
import com.exercise.msaccountmovement.repository.MovementRepository;

@Service
public class MovementServiceImpl implements IMovementService {

	@Autowired
	private MovementRepository movementRepository;

	@Autowired
	private IAccountService accountService;

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Movement saveMovement(MovementDTO movementDto) {

		Account account = accountService.findAccount(movementDto.getAccountId());

		Double value = movementDto.getValue();
		Double initialBalance = account.getInitialBalance();
		if (MovementType.RETIRO.equals(movementDto.getMovementType())) {
			if (value > initialBalance)
				throw new InsufficientBalanceException(value);
			account.setInitialBalance(initialBalance - value);
			value = -value;
		} else
			account.setInitialBalance(initialBalance + value);

		Movement movement = new Movement();
		movement.setMovementType(movementDto.getMovementType());
		movement.setValue(value);
		movement.setBalance(initialBalance);
		movement.setAccount(account);

		accountRepository.save(account);
		return movementRepository.save(movement);
	}

	@Override
	@Cacheable("findMovements")
	public List<Movement> findMovements() {
		return movementRepository.findAll();
	}

	@Override
	public void deleteMovement(Long movementId) {
		this.findMovement(movementId);
		movementRepository.deleteById(movementId);
	}

	@Override
	public Movement updateMovement(MovementDTO movementDto, Long movementId) {
		Movement movement = findMovement(movementId);
		movement.setMovementType(movementDto.getMovementType());
		Double value = movementDto.getValue();
		if (MovementType.RETIRO.equals(movementDto.getMovementType()))
			value = -value;
		movement.setValue(value);

		Account account = accountService.findAccount(movementDto.getAccountId());
		movement.setAccount(account);
		return movementRepository.save(movement);
	}

	@Override
	@Cacheable("findMovement")
	public Movement findMovement(Long movementId) {
		return movementRepository.findById(movementId).orElseThrow(() -> new MovementNotFoundException(movementId));
	}

}
