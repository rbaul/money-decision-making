package com.github.mdm.service;

import com.github.mdm.web.dtos.ChartInterval;
import com.github.mdm.web.dtos.ChartRange;
import com.github.mdm.web.dtos.TradingChartData;
import com.github.mdm.web.dtos.TradingDetails;

import java.util.Collection;
import java.util.Set;

public interface TradingAdapter {
	
	Set<TradingDetails> getSupportedTrading();
	
	Set<TradingChartData> getTradingChartData(Collection<String> ids, ChartInterval interval, ChartRange range);
}
