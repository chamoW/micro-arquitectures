package com.dc.hexagonal.hexagonalarchitecture.account.application.service.util;

import com.dc.hexagonal.hexagonalarchitecture.account.domain.Money;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyTransferProperties {
	
	 private Money maximumTransferThreshold = Money.of(1_000_000L);

}
