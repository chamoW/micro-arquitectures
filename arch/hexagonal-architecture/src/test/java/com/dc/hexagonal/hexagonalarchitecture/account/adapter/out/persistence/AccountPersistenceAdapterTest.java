package com.dc.hexagonal.hexagonalarchitecture.account.adapter.out.persistence;

import static com.dc.hexagonal.hexagonalarchitecture.common.AccountTestData.defaultAccount;
import static com.dc.hexagonal.hexagonalarchitecture.common.ActivityTestData.defaultActivity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.dc.hexagonal.hexagonalarchitecture.account.adapter.out.persistence.mapper.AccountMapper;
import com.dc.hexagonal.hexagonalarchitecture.account.adapter.out.persistence.model.ActivityJpaEntity;
import com.dc.hexagonal.hexagonalarchitecture.account.adapter.out.persistence.repository.ActivityRepository;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account.AccountId;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.ActivityWindow;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Money;

@DataJpaTest
@Import({ AccountPersistenceAdapter.class, AccountMapper.class })
class AccountPersistenceAdapterTest {

	@Autowired
	private AccountPersistenceAdapter adapter;

	@Autowired
	private ActivityRepository activityRepository;

	@Test
	void testLoadAccount() {
		Account account = adapter.loadAccount(new AccountId(1L), LocalDateTime.of(2022, 03, 11, 0, 0));

		assertThat(account.getActivityWindow().getActivities()).hasSize(1);
		assertEquals(account.calculateBalance(), Money.of(1000));
	}

	@Test
	void testUpdateActivities() {
		Account account = defaultAccount().withBaselineBalance(Money.of(555L)).withActivityWindow(
				new ActivityWindow(defaultActivity().withId(null).withMoney(Money.of(100L)).build())).build();

		adapter.updateActivities(account);

		assertAll(() -> {
			assertEquals(activityRepository.count(), 8);
			ActivityJpaEntity savedActivity = activityRepository.findAll().get(0);
			assertThat(savedActivity.getAmount()).isEqualTo(100L);
		});

	}

}
