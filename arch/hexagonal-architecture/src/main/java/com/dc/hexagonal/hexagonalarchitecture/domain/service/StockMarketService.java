package com.dc.hexagonal.hexagonalarchitecture.domain.service;

import java.math.BigDecimal;

import reactor.core.publisher.Mono;

public class StockMarketService {

	public Mono<BigDecimal> getStockMarket(String symbol, Integer quantity) {
		// TODO Auto-generated method stub
		return Mono.empty();
	}

}
