package com.dc.hexagonal.hexagonalarchitecture.account.application.service;

import java.time.LocalDateTime;

import com.dc.hexagonal.hexagonalarchitecture.account.application.port.in.GetAccountBalanceQueryUseCase;
import com.dc.hexagonal.hexagonalarchitecture.account.application.port.out.LoadAccountPort;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account.AccountId;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Money;

import lombok.RequiredArgsConstructor;

/**
 * Example of a read-only Use case implementation
 * @author wlopez
 *
 */
@RequiredArgsConstructor
public class GetAccountBalanceService implements GetAccountBalanceQueryUseCase {
	
	private final LoadAccountPort loadAccountPort;

	@Override
	public Money getAccountBalance(AccountId accountId) {
		return loadAccountPort.loadAccount(accountId, LocalDateTime.now())
				.calculateBalance();
	}


}
