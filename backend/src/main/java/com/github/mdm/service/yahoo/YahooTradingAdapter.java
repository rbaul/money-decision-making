package com.github.mdm.service.yahoo;

import com.github.mdm.service.TradingAdapter;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service(YahooTradingAdapter.YAHOO_TRADING)
public class YahooTradingAdapter implements TradingAdapter {
	private static final String allTradingsUS = "https://query2.finance.yahoo.com/v1/finance/trending/US?count=1000";
	
	private static final String allIndexesUrl = "https://query1.finance.yahoo.com/v7/finance/quote?formatted=true&crumb=XhsYC3kNK4J&lang=en-US&region=US&symbols=BTC-USD%2CETH-USD%2CAHT%2CIBM%2CAPPS%2C%5EGSPC%2CDOGE-USD%2CADA-USD%2COCGN%2C%5EIXIC%2CALEC%2C%5EDJI%2CXRP-USD%2CMRW.L%2CETH-CAD%2CAAPL%2CAMZN%2CARWR%2CDOGE-INR%2C%5ERUT%2CMSFT%2CHUT.TO%2CETH-EUR%2CGOOGL%2CSHIB-USD%2CGOOG%2CADA-EUR%2CVOO%2CMATIC-USD%2CETC-USD&fields=symbol%2ClongName%2CshortName%2CregularMarketPrice%2CregularMarketTime%2CregularMarketChange%2CregularMarketChangePercent%2CregularMarketVolume%2CaverageDailyVolume3Month%2CregularMarketDayRange%2CregularMarketOpen%2CfiftyTwoWeekLow%2CfiftyTwoWeekHigh%2CmarketCap%2CmessageBoardId%2CunderlyingSymbol%2CunderlyingExchangeSymbol%2CheadSymbolAsString%2Cuuid%2CtoCurrency%2CfromCurrency%2CtoExchange%2CfromExchange&corsDomain=finance.yahoo.com";
	
	private static final String indexDetailsUrl = "https://query1.finance.yahoo.com/v7/finance/quote?formatted=true&lang=en-US&region=US&symbols=%5EGSP";

//	private static final String chartDataUrl = "https://query1.finance.yahoo.com/v8/finance/chart/%5EGSPC?region=US&lang=en-US&includePrePost=false&interval=2m&useYfid=true&range=1d&corsDomain=finance.yahoo.com&.tsrc=finance";
	
	private static final String chartDataUrl = "https://query1.finance.yahoo.com/v8/finance/chart/%5EGSPC?interval=1d&range=1y";
	public static final String YAHOO_TRADING = "YAHOO-TRADING";
	
	public static final List<String> WORLD_INDICATORS = Arrays.asList("^GSPC", "^DJI", "^IXIC", "^NYA", "^XAX", "^BUK100P", "^RUT", "^VIX", "^FTSE",
			"^GDAXI", "^FCHI", "^STOXX50E", "^N100", "^BFX", "IMOEX.ME", "^N225", "^HSI", "000001.SS", "399001.SZ", "^STI", "^AXJO", "^AORD", "^BSESN",
			"^JKSE", "^KLSE", "^NZ50", "^KS11", "^TWII", "^GSPTSE", "^BVSP", "^MXX", "^IPSA", "^MERV", "^TA125.TA", "^CASE30", "^JN0U.JO");
	
	public static final List<String> STOCKS_INDICATORS = Arrays.asList("GOLD");
	public static final int MILISECONDS_IN_SECOND = 1000;
	
	/**
	 * Summary Details
	 * https://query2.finance.yahoo.com/v10/finance/quoteSummary/%5EDJI?formatted=true&crumb=XhsYC3kNK4J&lang=en-US&region=US&modules=price%2CsummaryDetail%2CpageViews%2CfinancialsTemplate&corsDomain=finance.yahoo.com
	 */
	
	private final RestTemplate restTemplate;
	
	public List<String> getAllIndexes() {
//		Map<String, Object> requestParams = new HashMap<>();
//		requestParams.put("count", 1000);
//		List<String> fieldToDisplay = Arrays.asList("symbol", "longName", "shortName");
//		requestParams.put("fields", String.join(",", fieldToDisplay));
//		IndexesYahoo indexesYahoo = restTemplate.exchange(getTargetUrlWithRequestParams("https://query2.finance.yahoo.com/v1/finance/trending/US", requestParams), HttpMethod.GET, null, IndexesYahoo.class).getBody();
//		if (indexesYahoo != null && indexesYahoo.getFinance().getResult() != null) {
//			List<String> symbols = indexesYahoo.getFinance().getResult().stream().findFirst()
//					.map(result -> result.getQuotes().stream().map(IndexesYahoo.Quote::getSymbol)
//							.collect(Collectors.toList())).orElse(new ArrayList<>());
//			return symbols;
//		} else {
//			return new ArrayList<>();
//		}
		List<String> indicators = new ArrayList<>(WORLD_INDICATORS);
		indicators.addAll(STOCKS_INDICATORS);
		return indicators;
	}
	
	/**
	 * https://query1.finance.yahoo.com/v7/finance/quote?formatted=true&lang=en-US&region=US&symbols=%5EGSP
	 * <p>
	 * // Get All
	 * https://query1.finance.yahoo.com/v7/finance/quote?symbols=%5EGSPC%2C%5EDJI%2C%5EVIX&fields=symbol%2ClongName%2CshortName
	 * https://query1.finance.yahoo.com/v7/finance/quote?symbols=%5EGSPC%2C%5EDJI%2C%5EVIX
	 */
	public IndexesDetailsYahooFormatted getDetailsFormatted(Collection<String> indexNames) {
		Map<String, Object> requestParams = new HashMap<>();
		requestParams.put("formatted", true);
		requestParams.put("lang", "en-US");
		requestParams.put("region", "US");
		requestParams.put("symbols", String.join(",", indexNames));
		return restTemplate.exchange(getTargetUrlWithRequestParams("https://query1.finance.yahoo.com/v7/finance/quote", requestParams),
				HttpMethod.GET, null, IndexesDetailsYahooFormatted.class).getBody();
	}
	
	/**
	 * // Get All
	 * https://query1.finance.yahoo.com/v7/finance/quote?symbols=%5EGSPC%2C%5EDJI%2C%5EVIX&fields=symbol%2ClongName%2CshortName
	 * https://query1.finance.yahoo.com/v7/finance/quote?symbols=%5EGSPC%2C%5EDJI%2C%5EVIX
	 */
	public List<IndexesDetailsYahoo.Result> getDetails(Collection<String> indexNames) {
		Map<String, Object> requestParams = new HashMap<>();
		List<String> fieldToDisplay = Arrays.asList("symbol", "longName", "shortName");
		requestParams.put("fields", String.join(",", fieldToDisplay));
		requestParams.put("symbols", String.join(",", indexNames));
		IndexesDetailsYahoo indexesDetailsYahoo = restTemplate.exchange(getTargetUrlWithRequestParams("https://query1.finance.yahoo.com/v7/finance/quote", requestParams),
				HttpMethod.GET, null, IndexesDetailsYahoo.class).getBody();
		if (indexesDetailsYahoo != null && indexesDetailsYahoo.getQuoteResponse() != null) {
			return indexesDetailsYahoo.getQuoteResponse().getResult();
		} else {
			return new ArrayList<>();
		}
	}
	
	/**
	 * https://query1.finance.yahoo.com/v7/finance/spark?symbols=%5EGSPC%2CBABA&range=1d&interval=5m
	 */
	public List<SparkResult.Result> getChartData(Collection<String> indexNames, Interval interval, Range range) {
		Map<String, Object> requestParams = new HashMap<>();
		requestParams.put("interval", interval.getRepresentString());
		requestParams.put("range", range.getRepresentString());
		requestParams.put("symbols", String.join(",", indexNames));
		String url = "https://query1.finance.yahoo.com/v7/finance/spark";
		SparkResult sparkResult = restTemplate.exchange(getTargetUrlWithRequestParams(url, requestParams),
				HttpMethod.GET, null, SparkResult.class).getBody();
		if (sparkResult != null && sparkResult.getSpark() != null && sparkResult.getSpark().getResult() != null) {
			return sparkResult.getSpark().getResult();
		} else {
			return new ArrayList<>();
		}
	}
	
	public ChartDataYahoo getChartData(String indexName, Interval interval, Range range) {
		Map<String, Object> requestParams = new HashMap<>();
		requestParams.put("interval", interval.getRepresentString());
		requestParams.put("range", range.getRepresentString());
		
		String url = "https://query1.finance.yahoo.com/v8/finance/chart/" + indexName;
		return restTemplate.exchange(getTargetUrlWithRequestParams(url, requestParams),
				HttpMethod.GET, null, ChartDataYahoo.class).getBody();
	}
	
	protected URI getTargetUrlWithRequestParams(String path, Map<String, Object> requestParams) {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(path);
		requestParams.forEach(uriComponentsBuilder::queryParam);
		return uriComponentsBuilder.build().toUri();
	}
	
	// ============= Impl ==============
	
	@Override
	public Set<TradingDetails> getSupportedTrading() {
		List<String> allSymbols = getAllIndexes();
		List<IndexesDetailsYahoo.Result> indexesDetailsYahoo = getDetails(allSymbols);
		return indexesDetailsYahoo.stream().map(result -> TradingDetails.builder()
						.id(result.getSymbol())
						.name(result.getShortName())
						.description(result.getFullExchangeName()).build())
				.collect(Collectors.toSet());
	}
	
	@Override
	public Set<TradingChartData> getTradingChartData(Collection<String> ids, ChartInterval interval, ChartRange range) {
		Interval interval1 = Interval.valueOf(interval.name());
		Range range1 = Range.valueOf(range.name());
		List<SparkResult.Result> chartData = getChartData(ids, interval1, range1);
		return chartData.stream().map(result -> {
			SparkResult.Response response = result.getResponse().get(0);
			List<Double> close = response.getIndicators().getQuote().get(0).getClose();
			return TradingChartData.builder()
					.id(result.getSymbol())
					.timestamp(response.getTimestamp().stream()
							.map(seconds -> seconds * MILISECONDS_IN_SECOND).collect(Collectors.toList()))
					.data(close).build();
		}).collect(Collectors.toSet());
	}
}
