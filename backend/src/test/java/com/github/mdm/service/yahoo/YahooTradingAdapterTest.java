package com.github.mdm.service.yahoo;

import com.github.mdm.config.RestTemplateConfig;
import com.github.mdm.service.yahoo.model.ChartDataYahoo;
import com.github.mdm.service.yahoo.model.IndexesDetailsYahoo;
import com.github.mdm.service.yahoo.model.IndexesDetailsYahooFormatted;
import com.github.mdm.service.yahoo.model.Interval;
import com.github.mdm.service.yahoo.model.Range;
import com.github.mdm.service.yahoo.model.SparkResult;
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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration(classes = {
		RestTemplateConfig.class,
		YahooTradingAdapter.class
})
@ExtendWith(SpringExtension.class)
class YahooTradingAdapterTest {
	
	@Autowired
	private YahooTradingAdapter yahooTradingAdapter;
	
	
	@Test
	void getAllIndexes() {
		List<String> allIndexes = yahooTradingAdapter.getAllIndexes();
		assertNotNull(allIndexes);
	}
	
	@Test
	void getDetailsFormatted() {
		IndexesDetailsYahooFormatted details = yahooTradingAdapter.getDetailsFormatted(Arrays.asList("^GSPC", "^DJI"));
		assertNotNull(details);
	}
	
	@Test
	void getDetails() {
		List<IndexesDetailsYahoo.Result> details = yahooTradingAdapter.getDetails(Arrays.asList("^GSPC", "^DJI"));
		assertNotNull(details);
	}
	
	@Test
	void getChartData() {
		ChartDataYahoo chartData = yahooTradingAdapter.getChartData("^GSPC", Interval._1_DAY, Range._2_YEAR);
		assertNotNull(chartData);
	}
	
	@Test
	void getChartDataSpark() {
		List<SparkResult.Result> chartData = yahooTradingAdapter.getChartData(Arrays.asList("^GSPC", "^DJI"), Interval._1_DAY, Range._2_YEAR);
		assertNotNull(chartData);
	}
	
	@Test
	void getSupportedTrading() {
		Set<TradingDetails> supportedTrading = yahooTradingAdapter.getSupportedTrading();
		assertFalse(CollectionUtils.isEmpty(supportedTrading));
	}
	
	@Test
	void getTradingChartData() {
		Set<TradingChartData> tradingChartData = yahooTradingAdapter.getTradingChartData(Arrays.asList("^GSPC", "^DJI"), ChartInterval._1_DAY, ChartRange._2_YEAR);
		assertFalse(CollectionUtils.isEmpty(tradingChartData));
	}
}