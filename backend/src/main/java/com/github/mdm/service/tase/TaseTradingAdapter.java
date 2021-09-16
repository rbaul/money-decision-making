package com.github.mdm.service.tase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.mdm.service.TradingAdapter;
import com.github.mdm.service.tase.model.ChartDataTase;
import com.github.mdm.service.tase.model.IndexDetailsTase;
import com.github.mdm.service.tase.model.TradeIndexesTase;
import com.github.mdm.web.dtos.ChartInterval;
import com.github.mdm.web.dtos.ChartRange;
import com.github.mdm.web.dtos.TradingChartData;
import com.github.mdm.web.dtos.TradingDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service(TaseTradingAdapter.TASE_TRADING)
public class TaseTradingAdapter implements TradingAdapter {
	private static final String allIndexesUrl = "https://api.tase.co.il/api/index/getindayindices"; // lang=0 Hebrew
//	private static final String allIndexesUrl = "https://api.tase.co.il/api/index/getindayindices?lang=1"; // lang=0 Hebrew
	
	private static final String indexDetailsUrl = "https://api.tase.co.il/api/index/details";
//	private static final String indexDetailsUrl = "https://api.tase.co.il/api/index/details?indexId=185&lang=1"; // lang=0 Hebrew
	
	private static final String chartDataUrl = "https://api.tase.co.il/api/ChartData/ChartData/";
	public static final String TASE_TRADING = "TASE-TRADING";
	public static final int MILISECONDS_IN_ONE_DAY = 24 * 60 * 60 * 1000;
	public static final int DAYS_IN_YEAR = 365;
	//	private static final String chartDataUrl = "https://api.tase.co.il/api/ChartData/ChartData/?ct=1&ot=2&lang=1&cf=0&cp=4&cv=0&cl=0&cgt=1&dFrom=03/07/2021&dTo=03/07/2021&oid=181"; // lang=0 Hebrew
//	                                            https://api.tase.co.il/api/ChartData/ChartData/?ct=1&cgt=1&cf=1&cv=0&ot=2&indexId=181&cl=0&lang=1&dFrom=03/07/2021&cp=4&dTo=5/07/2021
	private final RestTemplate restTemplate;
	
	private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public List<TradeIndexesTase> getAllIndexes() {
		Map<String, Object> requestParams = new HashMap<>();
		requestParams.put("lang", 1);
		List<TradeIndexesTase> tradeIndexesTases = restTemplate.exchange(getTargetUrlWithRequestParams(allIndexesUrl, requestParams), HttpMethod.GET, null, new ParameterizedTypeReference<List<TradeIndexesTase>>() {
		}).getBody();
		
		return tradeIndexesTases == null ? new ArrayList<>() : tradeIndexesTases.stream()
				.filter(tradeIndexesTase -> Objects.equals(tradeIndexesTase.getIndexCategoryType(), "02")).collect(Collectors.toList());
	}
	
	public IndexDetailsTase getIndexDetails(String indexId) {
		Map<String, Object> requestParams = new HashMap<>();
		requestParams.put("indexId", indexId);
		requestParams.put("lang", 1);
		return restTemplate.exchange(getTargetUrlWithRequestParams(indexDetailsUrl, requestParams), HttpMethod.GET, null, IndexDetailsTase.class).getBody();
	}
	
	/**
	 * // Day
	 * https://api.tase.co.il/api/ChartData/ChartData/?ct=1&ot=2&lang=1&cf=0&cp=0&cv=0&cl=0&cgt=1&dFrom=&dTo=&oid=142
	 * <p>
	 * // Week
	 * https://api.tase.co.il/api/ChartData/ChartData/?ct=1&ot=2&lang=1&cf=0&cp=4&cv=0&cl=0&cgt=1&dFrom=19/08/2021&dTo=19/08/2021&oid=142
	 * <p>
	 * //Month
	 * https://api.tase.co.il/api/ChartData/ChartData/?ct=1&ot=2&lang=1&cf=0&cp=4&cv=0&cl=0&cgt=1&dFrom=15/08/2021&dTo=19/08/2021&oid=142
	 * <p>
	 * // 3 Month
	 * https://api.tase.co.il/api/ChartData/ChartData/?ct=1&ot=2&lang=1&cf=0&cp=4&cv=0&cl=0&cgt=1&dFrom=20/08/2020&dTo=19/08/2021&oid=142
	 * <p>
	 * // Year
	 * https://api.tase.co.il/api/ChartData/ChartData/?ct=1&ot=2&lang=1&cf=0&cp=4&cv=0&cl=0&cgt=1&dFrom=20/05/2021&dTo=19/08/2021&oid=142
	 * <p>
	 * // 2 Years
	 * https://api.tase.co.il/api/ChartData/ChartData/?ct=1&ot=2&lang=1&cf=0&cp=5&cv=0&cl=0&cgt=1&dFrom=&dTo=&oid=142
	 * <p>
	 * // Specific 1.7.2021 - 31.7.2021
	 * https://api.tase.co.il/api/ChartData/ChartData/?ct=1&ot=2&lang=1&cf=0&cp=8&cv=0&cl=0&cgt=1&dFrom=31/07/2019&dTo=31/07/2021&oid=142
	 *
	 */
	public ChartDataTase getChartData(String indexId) {
//	ct=1&ot=2&lang=1&cf=0&cp=4&cv=0&cl=0&cgt=1&dFrom=03/07/2021&dTo=03/07/2021&oid=181
		Map<String, Object> requestParams = new HashMap<>();
		requestParams.put("oid", indexId);
		requestParams.put("lang", 1);
		requestParams.put("ct", 1);
		requestParams.put("ot", 2);
		requestParams.put("cf", 1);
		requestParams.put("cp", 4);
		requestParams.put("cv", 0);
		requestParams.put("cl", 0);
		requestParams.put("cgt", 1);
		requestParams.put("dFrom", "03/07/2021");
		requestParams.put("dTo", "17/07/2021");
		return restTemplate.exchange(getTargetUrlWithRequestParams(chartDataUrl, requestParams), HttpMethod.GET, null, ChartDataTase.class).getBody();
	}
	
	public ChartDataTase getChartDataByFromAndTo(String indexId, Date from, Date to) {
		Map<String, Object> requestParams = new HashMap<>();
		requestParams.put("oid", indexId);
		requestParams.put("lang", 1);
		requestParams.put("ct", 1);
		requestParams.put("ot", 2);
		requestParams.put("cf", 0);
		requestParams.put("cp", 8);
		requestParams.put("cv", 0);
		requestParams.put("cl", 0);
		requestParams.put("cgt", 1);
		requestParams.put("dFrom", dateToString(from));
		requestParams.put("dTo", dateToString(to));
		return restTemplate.exchange(getTargetUrlWithRequestParams(chartDataUrl, requestParams), HttpMethod.GET, null, ChartDataTase.class).getBody();
	}
	
	public ChartDataTase getChartDataOfLastDays(String indexId, int lastDays) {
		Date to = new Date();
		Date from = to;
		if (lastDays > 0) {
			long time = to.getTime();
			from = new Date(time - (long) lastDays * MILISECONDS_IN_ONE_DAY);
		}
		return getChartDataByFromAndTo(indexId, from, to);
	}
	
	/**
	 * https://api.tase.co.il/api/ChartData/ChartData/?ct=1&ot=2&lang=1&cf=0&cp=0&cv=0&cl=0&cgt=1&dFrom=&dTo=&oid=142
	 *
	 */
	public ChartDataTase getDailyChartData(String indexId) {
//	ct=1&ot=2&lang=1&cf=0&cp=4&cv=0&cl=0&cgt=1&dFrom=03/07/2021&dTo=03/07/2021&oid=181
		Map<String, Object> requestParams = new HashMap<>();
		requestParams.put("oid", indexId);
		requestParams.put("lang", 1);
		requestParams.put("ct", 1);
		requestParams.put("ot", 2);
		requestParams.put("cf", 0);
		requestParams.put("cp", 0);
		requestParams.put("cv", 0);
		requestParams.put("cl", 0);
		requestParams.put("cgt", 1);
//		requestParams.put("dFrom", "03/07/2021");
//		requestParams.put("dTo", "17/07/2021");
		return restTemplate.exchange(getTargetUrlWithRequestParams(chartDataUrl, requestParams), HttpMethod.GET, null, ChartDataTase.class).getBody();
	}
	
	private static String dateToString(Date date) {
		return date != null ? dateFormat.format(date) : null;
	}
	
	protected URI getTargetUrlWithRequestParams(String path, Map<String, Object> requestParams) {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(path);
		requestParams.forEach(uriComponentsBuilder::queryParam);
		return uriComponentsBuilder.build().toUri();
	}
	
	// ==============================
	
	
	@Override
	public Set<TradingDetails> getSupportedTrading() {
		List<TradeIndexesTase> allIndexes = getAllIndexes();
		return allIndexes.stream().map(index -> TradingDetails.builder()
				.id(index.getId())
				.name(index.getName()).build()).collect(Collectors.toSet());
	}
	
	@Override
	public Set<TradingChartData> getTradingChartData(Collection<String> ids, ChartInterval interval, ChartRange range) {
		return ids.stream().map(id -> {
			int lastDays = switch (range) {
				case _5_DAY -> 5;
				case _1_MONTH -> 30;
				case _3_MONTH -> 90;
				case _6_MONTH -> DAYS_IN_YEAR / 2;
				case _1_YEAR -> DAYS_IN_YEAR;
				case _2_YEAR -> 2 * DAYS_IN_YEAR;
				case _5_YEAR -> 5 * DAYS_IN_YEAR;
				case _10_YEAR -> 10 * DAYS_IN_YEAR;
				default -> 5 * DAYS_IN_YEAR;
			};
			ChartDataTase chartDataOfLastDays = getChartDataOfLastDays(id, lastDays);
			
			List<Double> data = new ArrayList<>();
			List<Long> timestamp = new ArrayList<>();
			
			chartDataOfLastDays.getPointsForHistoryChart()
					.forEach(pointsForHistoryChart -> {
						data.add(pointsForHistoryChart.getClosingRate());
						timestamp.add(pointsForHistoryChart.getTradeDate().getTime());
					});
			return TradingChartData.builder().id(id).data(data).timestamp(timestamp).build();
		}).collect(Collectors.toSet());
	}
}
