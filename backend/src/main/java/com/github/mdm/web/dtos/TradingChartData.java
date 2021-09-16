package com.github.mdm.web.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TradingChartData {
	@EqualsAndHashCode.Include
	private String id;
	
	private String name;
	
	private String description;
	
	private List<Double> data;
	
	private List<Long> timestamp;
	
}
