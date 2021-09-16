package com.github.mdm.service.yahoo.model;

import lombok.Data;

import java.util.List;

@Data
public class IndexesDetailsYahooFormatted {
	public QuoteResponse quoteResponse;
	
	@Data
	public static class FiftyTwoWeekLowChangePercent {
		public double raw;
		public String fmt;
	}
	
	@Data
	public static class RegularMarketOpen {
		public double raw;
		public String fmt;
	}
	
	@Data
	public static class RegularMarketTime {
		public int raw;
		public String fmt;
	}
	
	@Data
	public static class RegularMarketChangePercent {
		public double raw;
		public String fmt;
	}
	
	@Data
	public static class RegularMarketDayRange {
		public String raw;
		public String fmt;
	}
	
	@Data
	public static class FiftyTwoWeekLowChange {
		public double raw;
		public String fmt;
	}
	
	@Data
	public static class FiftyTwoWeekHighChangePercent {
		public double raw;
		public String fmt;
	}
	
	@Data
	public static class RegularMarketDayHigh {
		public double raw;
		public String fmt;
	}
	
	@Data
	public static class FiftyTwoWeekHigh {
		public double raw;
		public String fmt;
	}
	
	@Data
	public static class RegularMarketPreviousClose {
		public double raw;
		public String fmt;
	}
	
	@Data
	public static class FiftyTwoWeekHighChange {
		public double raw;
		public String fmt;
	}
	
	@Data
	public static class MarketCap {
		public Object raw;
		public String fmt;
		public String longFmt;
	}
	
	@Data
	public static class FiftyTwoWeekRange {
		public String raw;
		public String fmt;
	}
	
	@Data
	public static class RegularMarketChange {
		public double raw;
		public String fmt;
	}
	
	@Data
	public static class AverageDailyVolume3Month {
		public Object raw;
		public String fmt;
		public String longFmt;
	}
	
	@Data
	public static class FiftyTwoWeekLow {
		public double raw;
		public String fmt;
	}
	
	@Data
	public static class RegularMarketPrice {
		public double raw;
		public String fmt;
	}
	
	@Data
	public static class RegularMarketVolume {
		public Object raw;
		public String fmt;
		public String longFmt;
	}
	
	@Data
	public static class RegularMarketDayLow {
		public double raw;
		public String fmt;
	}
	
	@Data
	public static class SharesOutstanding {
		public Object raw;
		public String fmt;
		public String longFmt;
	}
	
	@Data
	public static class Result {
		public String fullExchangeName;
		public String symbol;
		public FiftyTwoWeekLowChangePercent fiftyTwoWeekLowChangePercent;
		public int gmtOffSetMilliseconds;
		public RegularMarketOpen regularMarketOpen;
		public String language;
		public RegularMarketTime regularMarketTime;
		public RegularMarketChangePercent regularMarketChangePercent;
		public String quoteType;
		public String uuid;
		public RegularMarketDayRange regularMarketDayRange;
		public FiftyTwoWeekLowChange fiftyTwoWeekLowChange;
		public FiftyTwoWeekHighChangePercent fiftyTwoWeekHighChangePercent;
		public RegularMarketDayHigh regularMarketDayHigh;
		public boolean tradeable;
		public String toExchange;
		public String currency;
		public String fromCurrency;
		public FiftyTwoWeekHigh fiftyTwoWeekHigh;
		public RegularMarketPreviousClose regularMarketPreviousClose;
		public String exchangeTimezoneName;
		public FiftyTwoWeekHighChange fiftyTwoWeekHighChange;
		public MarketCap marketCap;
		public String fromExchange;
		public FiftyTwoWeekRange fiftyTwoWeekRange;
		public RegularMarketChange regularMarketChange;
		public int exchangeDataDelayedBy;
		public AverageDailyVolume3Month averageDailyVolume3Month;
		public Object firstTradeDateMilliseconds;
		public String exchangeTimezoneShortName;
		public FiftyTwoWeekLow fiftyTwoWeekLow;
		public RegularMarketPrice regularMarketPrice;
		public String marketState;
		public RegularMarketVolume regularMarketVolume;
		public String market;
		public String quoteSourceName;
		public String messageBoardId;
		public String toCurrency;
		public int priceHint;
		public int sourceInterval;
		public RegularMarketDayLow regularMarketDayLow;
		public String exchange;
		public String region;
		public String shortName;
		public boolean triggerable;
		public SharesOutstanding sharesOutstanding;
		public String longName;
	}
	
	@Data
	public static class QuoteResponse {
		public List<Result> result;
		public Object error;
	}
}