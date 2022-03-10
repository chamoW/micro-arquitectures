package com.dc.hexagonal.hexagonalarchitecture.account.adapter.in;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dc.hexagonal.hexagonalarchitecture.account.application.port.in.SendMoneyUseCase;
import com.dc.hexagonal.hexagonalarchitecture.account.application.port.in.dto.SendMoneyDto;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account.AccountId;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Money;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SendMoneyController {

	private final SendMoneyUseCase sendMoneyUseCase;

	
	@PostMapping(path = "/accounts/sendMoney/{sourceAccountId}/{targetAccountId}/{amount}")
	 void sendMoney(
	 @PathVariable("sourceAccountId") Long sourceAccountId,
	 @PathVariable("targetAccountId") Long targetAccountId,
	 @PathVariable("amount") Long amount) {
	
	 SendMoneyDto command = new SendMoneyDto(
	 new AccountId(sourceAccountId),
	 new AccountId(targetAccountId),
	 Money.of(amount));
	
	 sendMoneyUseCase.sendMoney(command);
	 }

}
