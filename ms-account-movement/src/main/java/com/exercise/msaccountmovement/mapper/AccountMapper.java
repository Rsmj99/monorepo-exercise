package com.exercise.msaccountmovement.mapper;

import org.springframework.stereotype.Component;

import com.exercise.msaccountmovement.dto.AccountDTO;
import com.exercise.msaccountmovement.model.Account;

@Component
public class AccountMapper {

	public Account mapDTOToAccount(Account account, AccountDTO accountDTO) {
		account.setAccountNumber(accountDTO.getAccountNumber());
		account.setAccountType(accountDTO.getAccountType());
		account.setInitialBalance(accountDTO.getInitialBalance());
		account.setState(accountDTO.getState());
		account.setClientId(accountDTO.getClientId());
		return account;
	}

}
