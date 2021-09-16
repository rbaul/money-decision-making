package com.github.mdm.service.yahoo.model;

import lombok.Data;

import java.util.List;

@Data
public class ChartDataYahoo {
	public Chart chart;
	
	@Data
	public static class Pre {
		public String timezone;
		public int start;
		public int end;
		public int gmtoffset;
	}
	
	@Data
	public static class Regular {
		public String timezone;
		public int start;
		public int end;
		public int gmtoffset;
	}
	
	@Data
	public static class Post {
		public String timezone;
		public int start;
		public int end;
		public int gmtoffset;
	}
	
	@Data
	public static class CurrentTradingPeriod {
		public Pre pre;
		public Regular regular;
		public Post post;
	}
	
	@Data
	public static class Meta {
		public String currency;
		public String symbol;
		public String exchangeName;
		public String instrumentType;
		public int firstTradeDate;
		public int regularMarketTime;
		public int gmtoffset;
		public String timezone;
		public String exchangeTimezoneName;
		public double regularMarketPrice;
		public double chartPreviousClose;
		public double previousClose;
		public int scale;
		public int priceHint;
		public CurrentTradingPeriod currentTradingPeriod;
		public List<List<Object>> tradingPeriods;
		public Interval dataGranularity;
		public Range range;
		public List<Range> validRanges;
	}
	
	@Data
	public static class Quote {
		public List<Long> volume;
		public List<Double> open;
		public List<Double> high;
		public List<Double> low;
		public List<Double> close;
	}
	
	@Data
	public static class Indicators {
		public List<Quote> quote;
	}
	
	@Data
	public static class Result {
		public Meta meta;
		public List<Integer> timestamp;
		public Indicators indicators;
	}
	
	@Data
	public static class Chart {
		public List<Result> result;
		public Object error;
	}
}
