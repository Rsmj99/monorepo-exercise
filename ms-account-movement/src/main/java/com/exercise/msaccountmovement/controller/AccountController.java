package com.exercise.msaccountmovement.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.msaccountmovement.dto.AccountDTO;
import com.exercise.msaccountmovement.dto.ReporteDTO;
import com.exercise.msaccountmovement.model.Account;
import com.exercise.msaccountmovement.service.IAccountService;

@RefreshScope
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private IAccountService accountService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CompletableFuture<Account> save(@Valid @RequestBody AccountDTO accountDTO) {
		return accountService.createAccount(accountDTO);
	}

	@GetMapping
	public List<Account> findAll() {
		return accountService.findAccounts();
	}

	@GetMapping("{id}")
	public Account findById(@PathVariable Long id) {
		return accountService.findAccount(id);
	}

	@GetMapping("/reports")
	public CompletableFuture<List<ReporteDTO>> generateReport(
			@RequestParam("fecha-inicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("fecha-fin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam("cliente") Long clientId) {
		return accountService.findAccountsWithMovementDetailsByDateAndClient(startDate, endDate, clientId);
	}

	@PutMapping("{id}")
	public CompletableFuture<Account> update(@Valid @RequestBody AccountDTO accountDTO, @PathVariable Long id) {
		return accountService.updateAccount(accountDTO, id);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		accountService.deleteAccount(id);
	}
}
