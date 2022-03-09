package com.dc.hexagonal.hexagonalarchitecture.api;

import java.math.BigDecimal;
import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dc.hexagonal.hexagonalarchitecture.api.dto.GetStockPostionResponseDto;
import com.dc.hexagonal.hexagonalarchitecture.domain.model.StockPosition;
import com.dc.hexagonal.hexagonalarchitecture.domain.service.StockMarketService;
import com.dc.hexagonal.hexagonalarchitecture.domain.service.StockPositionService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class StockPositionController {

	private final StockPositionService stockPositionService;
	private final StockMarketService stockMarketService;

	@GetMapping("/stock-position/{symbol}")
	public Mono<GetStockPostionResponseDto> getStockPosition(@AuthenticationPrincipal Mono<Principal> principalMono,
			@PathVariable String symbol) {

		return principalMono.flatMap(principal -> stockPositionService.getStockPostion(principal.getName(), symbol))
				.zipWhen(stockPostion -> stockMarketService.getStockMarket(symbol, stockPostion.getQuantity()))
				.map(stockPostionAndMarkert -> {
					StockPosition stockPosition = stockPostionAndMarkert.getT1();
					BigDecimal stockMarket = stockPostionAndMarkert.getT2();
					
					return GetStockPostionResponseDto.builder()
							.symbol(symbol)
							.quantity(stockPosition.getQuantity())
							.currencyCode(stockPosition.getCurrencyCode())
							.cost(stockPosition.getCost()).marketVaule(stockMarket).build();
				});
	}

}
