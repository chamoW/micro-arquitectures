package com.dc.hexagonal.hexagonalarchitecture.account.application.service.util;

import com.dc.hexagonal.hexagonalarchitecture.account.domain.Money;

public class ThresholdExceededException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ThresholdExceededException(Money threshold, Money actual) {
		super(String.format(
				"Maximum threshold for transferring money exceeded: tried to transfer %s but threshold is %s!", actual,
				threshold));
	}

}
