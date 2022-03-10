package com.dc.hexagonal.hexagonalarchitecture.account.application.port.in;

import com.dc.hexagonal.hexagonalarchitecture.account.application.port.in.dto.SendMoneyDto;

public interface SendMoneyUseCase {

	boolean sendMoney(SendMoneyDto command);

}
