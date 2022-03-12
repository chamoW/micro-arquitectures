package com.dc.hexagonal.hexagonalarchitecture;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dc.hexagonal.hexagonalarchitecture.account.application.service.util.MoneyTransferProperties;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Money;

@Configuration
public class HexagonalArchitectureConfiguration {
	
	@Bean
	public MoneyTransferProperties moneyTransferProperties() {
		return new MoneyTransferProperties(Money.of(Long.MAX_VALUE));
	}

}
