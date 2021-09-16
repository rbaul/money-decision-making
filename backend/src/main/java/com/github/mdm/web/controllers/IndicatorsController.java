package com.github.mdm.web.controllers;

import com.github.mdm.service.TradingFactory;
import com.github.mdm.web.dtos.ChartInterval;
import com.github.mdm.web.dtos.ChartRange;
import com.github.mdm.web.dtos.TradingChartData;
import com.github.mdm.web.dtos.TradingDetails;
import com.github.rbaul.spring.boot.security.web.dtos.errors.ErrorDto;
import com.github.rozidan.springboot.logger.Loggable;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;

@Loggable(entered = true, ignore = Exception.class)
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/indicators")
public class IndicatorsController {
	private final TradingFactory tradingFactory;
	
	@ApiOperation(value = "Get all indicators")
	@ApiResponses({@ApiResponse(code = 200, message = "Retrieved all indicators")})
	@Secured({"ROLE_VIEWER_PRIVILEGE"})
	@GetMapping
	public Collection<TradingDetails> getAll() {
		return tradingFactory.getAll();
	}
	
	@ApiOperation(value = "Get Indicator")
	@ApiResponses({@ApiResponse(code = 200, message = "Retrieved Indicator"),
			@ApiResponse(code = 404, message = "Indicator Not Found", response = ErrorDto.class)})
	@Secured({"ROLE_VIEWER_PRIVILEGE"})
	@GetMapping("{indicatorId}")
	public TradingDetails get(@PathVariable String indicatorId) {
		return tradingFactory.getByIndicatorId(indicatorId);
	}
	
	@ApiOperation(value = "Get Indicators Chart data")
	@ApiResponses({@ApiResponse(code = 200, message = "Retrieved Indicators Chart data")})
	@Secured({"ROLE_VIEWER_PRIVILEGE"})
	@GetMapping("chart")
	public Collection<TradingChartData> get(@RequestParam Set<String> indicatorIds,
											@RequestParam(required = false, defaultValue = "_1_DAY") ChartInterval interval,
											@RequestParam(required = false, defaultValue = "_1_YEAR") ChartRange range) {
		return tradingFactory.getChartData(indicatorIds, interval, range);
	}
	
	@ApiOperation(value = "Get default indicators")
	@ApiResponses({@ApiResponse(code = 200, message = "Retrieved all default indicators")})
	@Secured({"ROLE_VIEWER_PRIVILEGE"})
	@GetMapping("defaults")
	public Collection<String> getDefaultIndicators() {
		return tradingFactory.getDefault();
	}
}
