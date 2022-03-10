package com.dc.hexagonal.hexagonalarchitecture.account.application.port.out;

import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account.AccountId;

public interface AccountLock {

	void lockAccount(AccountId sourceAccountId);

	void releaseAccount(AccountId sourceAccountId);

}
