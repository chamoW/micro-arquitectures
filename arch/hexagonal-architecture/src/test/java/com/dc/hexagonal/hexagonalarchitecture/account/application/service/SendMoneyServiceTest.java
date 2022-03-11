package com.dc.hexagonal.hexagonalarchitecture.account.application.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dc.hexagonal.hexagonalarchitecture.account.application.port.in.dto.SendMoneyDto;
import com.dc.hexagonal.hexagonalarchitecture.account.application.port.out.AccountLock;
import com.dc.hexagonal.hexagonalarchitecture.account.application.port.out.LoadAccountPort;
import com.dc.hexagonal.hexagonalarchitecture.account.application.port.out.UpdateAccountStatePort;
import com.dc.hexagonal.hexagonalarchitecture.account.application.service.util.MoneyTransferProperties;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account.AccountId;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Money;

@ExtendWith(MockitoExtension.class)
class SendMoneyServiceTest {

	@Mock
	private LoadAccountPort loadAccountPort;

	@Mock
	private UpdateAccountStatePort updateAccountStatePort;

	@Mock
	private AccountLock accountLock;

	@Mock
	private MoneyTransferProperties moneyTransferProperties;

	@InjectMocks
	private SendMoneyService sendMoneyService;

	@Test
	void testSendMoney() {

		Account sourceAccount = givenAnAccountWithId(new AccountId(1L));
		Account targetAccount = givenAnAccountWithId(new AccountId(2L));
		Money money = Money.of(200L);

		SendMoneyDto sendMoneyDto = new SendMoneyDto(sourceAccount.getId().get(), targetAccount.getId().get(), money);

		when(moneyTransferProperties.getMaximumTransferThreshold()).thenReturn(Money.of(Long.MAX_VALUE));
		when(sourceAccount.withdraw(any(Money.class), any(AccountId.class))).thenReturn(true);
		when(targetAccount.deposit(any(Money.class), any(AccountId.class))).thenReturn(true);

		boolean response = sendMoneyService.sendMoney(sendMoneyDto);
		
		assertAll(() -> {
			assertTrue(response);
			
		});
	}
	
	@Test
	void testSendMoneyFailsWithdraw() {

		Account sourceAccount = givenAnAccountWithId(new AccountId(1L));
		Account targetAccount = givenAnAccountWithId(new AccountId(2L));
		Money money = Money.of(200L);

		SendMoneyDto sendMoneyDto = new SendMoneyDto(sourceAccount.getId().get(), targetAccount.getId().get(), money);

		when(moneyTransferProperties.getMaximumTransferThreshold()).thenReturn(Money.of(Long.MAX_VALUE));
		when(sourceAccount.withdraw(any(Money.class), any(AccountId.class))).thenReturn(false);

		boolean response = sendMoneyService.sendMoney(sendMoneyDto);
		
		assertAll(() -> {
			assertFalse(response);
			
		});
	}
	
	@Test
	void testSendMoneyFailsDeposit() {

		Account sourceAccount = givenAnAccountWithId(new AccountId(1L));
		Account targetAccount = givenAnAccountWithId(new AccountId(2L));
		Money money = Money.of(200L);

		SendMoneyDto sendMoneyDto = new SendMoneyDto(sourceAccount.getId().get(), targetAccount.getId().get(), money);

		when(moneyTransferProperties.getMaximumTransferThreshold()).thenReturn(Money.of(Long.MAX_VALUE));
		when(sourceAccount.withdraw(any(Money.class), any(AccountId.class))).thenReturn(true);
		when(targetAccount.deposit(any(Money.class), any(AccountId.class))).thenReturn(false);

		boolean response = sendMoneyService.sendMoney(sendMoneyDto);
		
		assertAll(() -> {
			assertFalse(response);
			
		});
	}


	private Account givenAnAccountWithId(AccountId id) {
		Account account = mock(Account.class);
		given(account.getId()).willReturn(Optional.of(id));
		given(loadAccountPort.loadAccount(eq(account.getId().get()), any(LocalDateTime.class))).willReturn(account);
		return account;
	}

}
