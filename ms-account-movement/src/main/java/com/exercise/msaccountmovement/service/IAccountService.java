package com.exercise.msaccountmovement.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.exercise.msaccountmovement.dto.AccountDTO;
import com.exercise.msaccountmovement.dto.ReporteDTO;
import com.exercise.msaccountmovement.model.Account;

public interface IAccountService {

	CompletableFuture<Account> createAccount(AccountDTO accountDTO);

	CompletableFuture<Account> updateAccount(AccountDTO accountDTO, Long accountId);

	List<Account> findAccounts();

	void deleteAccount(Long accountId);

	CompletableFuture<List<ReporteDTO>> findAccountsWithMovementDetailsByDateAndClient(Date startDate, Date endDate,
			Long clientId);

	Account findAccount(Long accountId);
}
