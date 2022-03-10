package com.dc.hexagonal.hexagonalarchitecture.account.application.port.in;

import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account.AccountId;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Money;

/**
 * Read only use case
 * @author wlopez
 *
 */
public interface GetAccountBalanceQueryUseCase {
	Money getAccountBalance(AccountId accountId);

}
