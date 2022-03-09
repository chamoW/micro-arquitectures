package com.dc.hexagonal.hexagonalarchitecture.api.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetStockPostionResponseDto {
	
	private String symbol;
	private Integer quantity;
	private String currencyCode;
	private BigDecimal cost;
	private BigDecimal marketVaule;
	

}
