package com.dc.hexagonal.hexagonalarchitecture.account.application.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import com.dc.hexagonal.hexagonalarchitecture.account.application.port.in.SendMoneyUseCase;
import com.dc.hexagonal.hexagonalarchitecture.account.application.port.in.dto.SendMoneyDto;
import com.dc.hexagonal.hexagonalarchitecture.account.application.port.out.AccountLock;
import com.dc.hexagonal.hexagonalarchitecture.account.application.port.out.LoadAccountPort;
import com.dc.hexagonal.hexagonalarchitecture.account.application.port.out.UpdateAccountStatePort;
import com.dc.hexagonal.hexagonalarchitecture.account.application.service.util.MoneyTransferProperties;
import com.dc.hexagonal.hexagonalarchitecture.account.application.service.util.ThresholdExceededException;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account.AccountId;
import com.dc.hexagonal.hexagonalarchitecture.common.UseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
@Transactional
public class SendMoneyService implements SendMoneyUseCase {

	private final LoadAccountPort loadAccountPort;
	private final UpdateAccountStatePort updateAccountStatePort;
	private final AccountLock accountLock;
	private final MoneyTransferProperties moneyTransferProperties;

	@Override
	public boolean sendMoney(SendMoneyDto sendMonetDto) {
		checkThreshold(sendMonetDto);

		LocalDateTime baselineDate = LocalDateTime.now().minusDays(10);

		Account sourceAccount = loadAccountPort.loadAccount(sendMonetDto.getSourceAccountId(), baselineDate);

		Account targetAccount = loadAccountPort.loadAccount(sendMonetDto.getTargetAccountId(), baselineDate);

		AccountId sourceAccountId = sourceAccount.getId()
				.orElseThrow(() -> new IllegalStateException("expected source account ID not to be empty"));

		AccountId targetAccountId = targetAccount.getId()
				.orElseThrow(() -> new IllegalStateException("expected target account ID not to be empty"));

		accountLock.lockAccount(sourceAccountId);
		if (!sourceAccount.withdraw(sendMonetDto.getMoney(), targetAccountId)) {
			accountLock.releaseAccount(sourceAccountId);
			return false;
		}

		accountLock.lockAccount(targetAccountId);
		if (!targetAccount.deposit(sendMonetDto.getMoney(), sourceAccountId)) {
			accountLock.releaseAccount(sourceAccountId);
			accountLock.releaseAccount(targetAccountId);
			return false;
		}

		updateAccountStatePort.updateActivities(sourceAccount);
		updateAccountStatePort.updateActivities(targetAccount);

		accountLock.releaseAccount(sourceAccountId);
		accountLock.releaseAccount(targetAccountId);

		return true;

	}

	private void checkThreshold(SendMoneyDto command) {
		if (command.getMoney().isGreaterThan(moneyTransferProperties.getMaximumTransferThreshold())) {
			throw new ThresholdExceededException(moneyTransferProperties.getMaximumTransferThreshold(),
					command.getMoney());
		}

	}

}
