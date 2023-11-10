package com.exercise.msaccountmovement.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exercise.msaccountmovement.dto.ReporteDTO;
import com.exercise.msaccountmovement.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query("SELECT NEW com.exercise.msaccountmovement.dto.ReporteDTO(CAST(m.date AS date), a.clientId, '', a.accountNumber, a.accountType, m.balance, a.state, m.value, m.balance + m.value) FROM Account a INNER JOIN a.movements m WHERE CAST(m.date AS date) BETWEEN :startDate AND :endDate AND a.clientId = :clientId")
	List<ReporteDTO> findAccountsWithMovementDetailsByDateAndClient(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("clientId") Long clientId);

}
