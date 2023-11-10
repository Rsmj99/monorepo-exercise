package com.exercise.msaccountmovement.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.exercise.msaccountmovement.dto.AccountDTO;
import com.exercise.msaccountmovement.dto.ReporteDTO;
import com.exercise.msaccountmovement.exception.AccountNotFoundException;
import com.exercise.msaccountmovement.exception.ClientNotFoundException;
import com.exercise.msaccountmovement.exception.FeignCommunicationException;
import com.exercise.msaccountmovement.mapper.AccountMapper;
import com.exercise.msaccountmovement.model.Account;
import com.exercise.msaccountmovement.repository.AccountRepository;

import feign.FeignException;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ClientPersonRest clientPersonRest;

	@Autowired
	private AccountMapper accountMapper;

	@Override
	public CompletableFuture<Account> createAccount(AccountDTO accountDTO) {
		return getClientNameAsync(accountDTO.getClientId()).thenApplyAsync(name -> {
			// Cliente encontrado, procede a actualizar la cuenta
			Account account = accountMapper.mapDTOToAccount(new Account(), accountDTO);
			return accountRepository.save(account);
		});
	}

	@Override
	@Cacheable("findAccounts")
	public List<Account> findAccounts() {
		return accountRepository.findAll();
	}

	@Override
	@Cacheable("findAccount")
	public Account findAccount(Long accountId) {
		return accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
	}

	@Override
	public void deleteAccount(Long accountId) {
		this.findAccount(accountId);
		accountRepository.deleteById(accountId);
	}

	@Async
	public CompletableFuture<List<ReporteDTO>> findAccountsWithMovementDetailsByDateAndClient(Date startDate,
			Date endDate, Long clientId) {
		List<ReporteDTO> reporteDTOs = accountRepository.findAccountsWithMovementDetailsByDateAndClient(startDate,
				endDate, clientId);

		// Map para almacenar CompletableFuture de las llamadas asíncronas
		Map<Long, CompletableFuture<String>> clientFutures = new ConcurrentHashMap<>();

		// Realizar llamadas asíncronas para obtener los nombres de los clientes
		List<CompletableFuture<Void>> futures = reporteDTOs.stream().map(reporteDTO -> clientFutures
				.computeIfAbsent(reporteDTO.getClientId(), this::getClientNameAsync).thenAccept(reporteDTO::setClient))
				.collect(Collectors.toList());

		// Esperar a que todas las llamadas asíncronas se completen
		CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
		return allOf.thenApply(result -> reporteDTOs);
	}

	private CompletableFuture<String> getClientNameAsync(Long clientId) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return clientPersonRest.findById(clientId).getName();
			} catch (FeignException.NotFound ex) {
				throw new ClientNotFoundException(clientId);
			} catch (FeignException e) {
				throw new FeignCommunicationException();
			}
		});
	}

	@Override
	public CompletableFuture<Account> updateAccount(AccountDTO accountDTO, Long accountId) {
		return getClientNameAsync(accountDTO.getClientId()).thenApplyAsync(name -> {
			// Cliente encontrado, procede a actualizar la cuenta
			Account account = accountMapper.mapDTOToAccount(findAccount(accountId), accountDTO);
			return accountRepository.save(account);
		});
	}
}
