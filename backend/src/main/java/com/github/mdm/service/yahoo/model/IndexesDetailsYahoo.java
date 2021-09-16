package com.github.mdm.service.yahoo.model;

import lombok.Data;

import java.util.List;

@Data
public class IndexesDetailsYahoo {
	public QuoteResponse quoteResponse;
	
	@Data
	public static class QuoteResponse {
		public List<Result> result;
		public Object error;
	}
	
	@Data
	public static class Result {
		public String language;
		public String region;
		public String quoteType;
		public String quoteSourceName;
		public Boolean triggerable;
		public String currency;
		public String messageBoardId;
		public String exchangeTimezoneName;
		public String exchangeTimezoneShortName;
		public Long gmtOffSetMilliseconds;
		public String market;
		public Boolean esgPopulated;
		public String shortName;
		public String exchange;
		public String marketState;
		public String fiftyTwoWeekRange;
		public Double fiftyTwoWeekHighChange;
		public Double fiftyTwoWeekHighChangePercent;
		public Double fiftyTwoWeekLow;
		public Double fiftyTwoWeekHigh;
		public Double fiftyDayAverage;
		public Double fiftyDayAverageChange;
		public Double fiftyDayAverageChangePercent;
		public Double twoHundredDayAverage;
		public Double twoHundredDayAverageChange;
		public Double twoHundredDayAverageChangePercent;
		public Long sourceInterval;
		public Long exchangeDataDelayedBy;
		public Boolean tradeable;
		public Long firstTradeDateMilliseconds;
		public Long regularMarketVolume;
		public Long priceHint;
		public Double regularMarketChange;
		public Double regularMarketChangePercent;
		public Long regularMarketTime;
		public Double regularMarketPrice;
		public Double regularMarketDayHigh;
		public String regularMarketDayRange;
		public Double regularMarketDayLow;
		public Long regularMarketPreviousClose;
		public Double bid;
		public Double ask;
		public Long bidSize;
		public Long askSize;
		public String fullExchangeName;
		public Double regularMarketOpen;
		public Long averageDailyVolume3Month;
		public Long averageDailyVolume10Day;
		public Double fiftyTwoWeekLowChange;
		public Double fiftyTwoWeekLowChangePercent;
		public String symbol;
	}
}
