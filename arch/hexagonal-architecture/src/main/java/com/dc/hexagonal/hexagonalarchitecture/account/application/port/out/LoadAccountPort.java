package com.dc.hexagonal.hexagonalarchitecture.account.application.port.out;

import java.time.LocalDateTime;

import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account.AccountId;

public interface LoadAccountPort {

	Account loadAccount(AccountId accountId, LocalDateTime baselineDate);

}
