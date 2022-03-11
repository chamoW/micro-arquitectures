package com.dc.hexagonal.hexagonalarchitecture.account.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account.AccountId;
import com.dc.hexagonal.hexagonalarchitecture.common.AccountTestData;
import com.dc.hexagonal.hexagonalarchitecture.common.ActivityTestData;

class AccountTest {

	@Test
	void testWithdrawalSucceds() {
		
		AccountId accountId = new AccountId(1L);
		
		Activity activity1 =  ActivityTestData.defaultActivity()
				.withTargetAccount(accountId)
				.withMoney(Money.of(100L)).build();
		
		Activity activity2 =  ActivityTestData.defaultActivity()
				.withTargetAccount(accountId)
				.withMoney(Money.of(200L)).build();
		
		ActivityWindow activityWindow = new ActivityWindow(activity1, activity2);
		
		
		Account account = AccountTestData.defaultAccount()
		.withAccountId(accountId)
		.withBaselineBalance(Money.of(1000l))
		.withActivityWindow(activityWindow).build();
		
		boolean success = account.withdraw( Money.of(400L), new AccountId(99L));
		
		assertTrue(success);
		assertThat(account.getActivityWindow().getActivities()).hasSize(3);
		assertThat(account.calculateBalance()).isEqualTo(Money.of(900L));
		
		
		
	}

}
