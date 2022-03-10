package com.dc.hexagonal.hexagonalarchitecture.account.application.port.out;

import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account;

public interface UpdateAccountStatePort {

	void updateActivities(Account sourceAccount);

}
