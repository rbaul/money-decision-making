package com.github.mdm.service.tase;

import com.github.mdm.config.RestTemplateConfig;
import com.github.mdm.service.tase.model.ChartDataTase;
import com.github.mdm.service.tase.model.IndexDetailsTase;
import com.github.mdm.service.tase.model.TradeIndexesTase;
import com.github.mdm.web.dtos.ChartInterval;
import com.github.mdm.web.dtos.ChartRange;
import com.github.mdm.web.dtos.TradingChartData;
import com.github.mdm.web.dtos.TradingDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration(classes = {
		RestTemplateConfig.class,
		TaseTradingAdapter.class
})
@ExtendWith(SpringExtension.class)
class TaseTradingAdapterTest {
	
	@Autowired
	private TaseTradingAdapter taseTradingAdapter;
	
	@Test
	void getAllIndexes() {
		List<TradeIndexesTase> allIndexes = taseTradingAdapter.getAllIndexes();
		assertNotNull(allIndexes);
		Map<String, String> collect = allIndexes.stream().collect(Collectors.toMap(TradeIndexesTase::getName, TradeIndexesTase::getId));
		System.out.println(collect);
	}
	
	@Test
	void getIndexDetails() {
		IndexDetailsTase indexDetails = taseTradingAdapter.getIndexDetails("181");
		assertNotNull(indexDetails);
	}
	
	@Test
	void getChartData() {
		ChartDataTase chartData = taseTradingAdapter.getChartData("181");
//		chartData.getPointsForHistoryChart().stream().map(pointsForHistoryChart -> pointsForHistoryChart.get)
		assertNotNull(chartData);
	}
	
	@Test
	void getChartDataOfLastDays() {
		ChartDataTase chartData = taseTradingAdapter.getChartDataOfLastDays("181", 777);
		assertNotNull(chartData);
	}
	
	@Test
	void getDailyChartData() {
		ChartDataTase chartData = taseTradingAdapter.getDailyChartData("181");
		assertNotNull(chartData);
	}
	
	@Test
	void getSupportedTrading() {
		Set<TradingDetails> supportedTrading = taseTradingAdapter.getSupportedTrading();
		assertFalse(CollectionUtils.isEmpty(supportedTrading));
	}
	
	@Test
	void getTradingChartData() {
		Set<TradingChartData> tradingChartData = taseTradingAdapter.getTradingChartData(Arrays.asList("181", "161"), ChartInterval._1_DAY, ChartRange._1_YEAR);
		assertFalse(CollectionUtils.isEmpty(tradingChartData));
	}
}