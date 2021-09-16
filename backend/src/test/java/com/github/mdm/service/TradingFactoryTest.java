package com.github.mdm.service;

import com.github.mdm.config.RestTemplateConfig;
import com.github.mdm.service.tase.TaseTradingAdapter;
import com.github.mdm.service.yahoo.YahooTradingAdapter;
import com.github.mdm.web.dtos.ChartInterval;
import com.github.mdm.web.dtos.ChartRange;
import com.github.mdm.web.dtos.TradingChartData;
import com.github.mdm.web.dtos.TradingDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ContextConfiguration(classes = {
		RestTemplateConfig.class,
		TaseTradingAdapter.class,
		YahooTradingAdapter.class,
		TradingFactory.class
})
@ExtendWith(SpringExtension.class)
class TradingFactoryTest {
	
	@Autowired
	private TradingFactory testedClass;
	
	@BeforeEach
	void before() {
		testedClass.init();
	}
	
	@Test
	void getAll() {
		Collection<TradingDetails> details = testedClass.getAll();
		assertFalse(CollectionUtils.isEmpty(details));
	}
	
	@Test
	void getChartData() {
		List<String> ids = Arrays.asList("^GSPC", "^DJI", "143");
		Collection<TradingChartData> chartData = testedClass.getChartData(ids, ChartInterval._1_DAY, ChartRange._2_YEAR);
		assertFalse(CollectionUtils.isEmpty(chartData));
		assertEquals(ids.size(), chartData.size());
	}
}