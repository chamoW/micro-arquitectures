package com.dc.hexagonal.hexagonalarchitecture.domain.service;

import com.dc.hexagonal.hexagonalarchitecture.domain.model.StockPosition;

import reactor.core.publisher.Mono;

public class StockPositionService {
	
	public Mono<StockPosition> getStockPostion(String user, String symbol) {
		return Mono.empty();
	}

}
