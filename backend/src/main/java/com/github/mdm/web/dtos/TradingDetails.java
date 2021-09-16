package com.github.mdm.web.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TradingDetails {
	@EqualsAndHashCode.Include
	private String id;
	
	private String name;
	
	private String description;
	
	private String tradingAdapterName;
	
}
