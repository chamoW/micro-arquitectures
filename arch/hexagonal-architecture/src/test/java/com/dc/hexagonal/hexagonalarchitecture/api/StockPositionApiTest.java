package com.dc.hexagonal.hexagonalarchitecture.api;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.dc.hexagonal.hexagonalarchitecture.api.dto.GetStockPostionResponseDto;
import com.dc.hexagonal.hexagonalarchitecture.domain.model.StockPosition;
import com.dc.hexagonal.hexagonalarchitecture.domain.service.StockMarketService;
import com.dc.hexagonal.hexagonalarchitecture.domain.service.StockPositionService;
import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@WebFluxTest
public class StockPositionApiTest {

	@Autowired
	private WebTestClient client;

	// Domain service
	@MockBean
	private StockPositionService stockPositionService;

	@MockBean
	private StockMarketService stockMarketService;

	private Faker faker = Faker.instance();

	@Test
	@WithMockUser("wlopez")
	void testGetPosition() {

		// given
		String symbol = "aapl";
		StockPosition fakeStockPostion = setupStockPosition(symbol);
		BigDecimal fakeMarketValue = setupMarketValue(symbol, fakeStockPostion);
		// when
		client.get().uri("/stock-position/" + symbol).accept(MediaType.APPLICATION_JSON).exchange()

				// then
				.expectStatus().isOk().expectBody(GetStockPostionResponseDto.class).consumeWith(response -> {
					log.info("RESPONSE: {}", response);
				}).value(dto -> assertAll(() -> {
					assertEquals(dto.getSymbol(), symbol);
					assertEquals(dto.getQuantity(), fakeStockPostion.getQuantity());
					assertEquals(dto.getCurrencyCode(), fakeStockPostion.getCurrencyCode());
					assertEquals(dto.getCost().doubleValue(), fakeStockPostion.getCost().doubleValue());
					assertEquals(dto.getMarketVaule(), fakeMarketValue);
				}));

	}

	@Test
	@WithMockUser("wlopez")
	void testEmptyBody() {
		// given
		String symbol = "aapl";

		when(stockPositionService.getStockPostion("wlopez", symbol)).thenReturn(Mono.empty());
		when(stockMarketService.getStockMarket(eq(symbol), any()))
				.thenReturn(Mono.just(BigDecimal.valueOf(faker.number().randomDouble(4, 0, 1000000))));

		// when
		client.get().uri("/stock-position/" + symbol).accept(MediaType.APPLICATION_JSON).exchange()
				// then
				.expectStatus().isOk().expectBody(Void.class);

	}

	@Test
	void testNonAuthorized() {
		// given
		String symbol = "aapl";

		setupStockPosition(symbol);

		// when
		client.get().uri("/stock-position/" + symbol).accept(MediaType.APPLICATION_JSON).exchange()
				// then
				.expectStatus().isUnauthorized();

	}

	private StockPosition setupStockPosition(String symbol) {
		StockPosition fake = StockPosition.builder().symbol(symbol).quantity(0).currencyCode(faker.currency().code())
				.cost(BigDecimal.valueOf(faker.number().randomDouble(4, 0, 1000000))).build();

		String user = "wlopez";
		when(stockPositionService.getStockPostion(user, symbol)).thenReturn(Mono.just(fake));

		return fake;

	}

	private BigDecimal setupMarketValue(String symbol, StockPosition fake) {

		BigDecimal fakeNumber = BigDecimal.valueOf(faker.number().randomDouble(4, 0, 100000));
		when(stockMarketService.getStockMarket(symbol, fake.getQuantity())).thenReturn(Mono.just(fakeNumber));

		return fakeNumber;

	}

}
