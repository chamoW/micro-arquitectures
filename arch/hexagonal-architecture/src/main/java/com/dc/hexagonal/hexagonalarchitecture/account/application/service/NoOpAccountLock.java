package com.dc.hexagonal.hexagonalarchitecture.account.application.service;

import org.springframework.stereotype.Component;

import com.dc.hexagonal.hexagonalarchitecture.account.application.port.out.AccountLock;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account.AccountId;

@Component
public class NoOpAccountLock implements AccountLock {

	@Override
	public void lockAccount(AccountId sourceAccountId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void releaseAccount(AccountId sourceAccountId) {
		// TODO Auto-generated method stub

	}

}
