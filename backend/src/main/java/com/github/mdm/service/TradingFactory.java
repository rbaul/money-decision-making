package com.github.mdm.service;

import com.github.mdm.config.MdmProperties;
import com.github.mdm.web.dtos.ChartInterval;
import com.github.mdm.web.dtos.ChartRange;
import com.github.mdm.web.dtos.TradingChartData;
import com.github.mdm.web.dtos.TradingDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class TradingFactory {
	
	private final MdmProperties mdmProperties;
	
	private final Map<String, TradingAdapter> tradingAdapters;
	
	private Map<String, TradingDetails> tradingDetails = new HashMap<>();
	
	public void init() {
		tradingDetails.clear();
		
		if (!CollectionUtils.isEmpty(tradingAdapters)) {
			tradingAdapters.forEach((tradingAdapterName, tradingAdapter) -> {
				Set<TradingDetails> supportedTrading = tradingAdapter.getSupportedTrading();
				supportedTrading.forEach(tradingDetail -> {
					tradingDetail.setTradingAdapterName(tradingAdapterName);
					tradingDetails.put(tradingDetail.getId(), tradingDetail);
				});
			});
		}
	}
	
	public Collection<TradingDetails> getAll() {
		return tradingDetails.values();
	}
	
	public Collection<TradingChartData> getChartData(Collection<String> ids, ChartInterval interval, ChartRange range) {
		Map<String, List<TradingDetails>> map = new HashMap<>();
		
		tradingDetails.entrySet().stream()
				.filter(entry -> ids.contains(entry.getKey()))
				.forEach(entry -> {
					String tradingAdapterName = entry.getValue().getTradingAdapterName();
					if (!map.containsKey(tradingAdapterName)) {
						map.put(tradingAdapterName, new ArrayList<>());
					}
					map.get(tradingAdapterName).add(entry.getValue());
				});
		
		Set<TradingChartData> result = new HashSet<>();
		map.forEach((s, tradingDetailsList) -> {
			TradingAdapter tradingAdapter = tradingAdapters.get(s);
			Set<String> indexes = tradingDetailsList.stream()
					.map(TradingDetails::getId).collect(Collectors.toSet());
			result.addAll(tradingAdapter.getTradingChartData(indexes, interval, range));
		});
		
		// Update info
		result.forEach(tradingChartData -> {
			TradingDetails tradingDetails = this.tradingDetails.get(tradingChartData.getId());
			tradingChartData.setName(tradingDetails.getName());
			tradingChartData.setDescription(tradingDetails.getDescription());
		});
		
		return result;
	}
	
	public TradingDetails getByIndicatorId(String indicatorId) {
		return tradingDetails.get(indicatorId);
	}
	
	public Collection<String> getDefault() {
		return mdmProperties.getDefaultValues();
	}
}
