package com.dc.hexagonal.hexagonalarchitecture.domain.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockPosition {
	
	private String symbol;
	private Integer quantity;
	private String currencyCode;
	private BigDecimal cost;
	

}
