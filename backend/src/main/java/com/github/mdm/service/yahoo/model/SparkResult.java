package com.github.mdm.service.yahoo.model;

import lombok.Data;

import java.util.List;

@Data
public class SparkResult {
	public Spark spark;
	
	@Data
	public static class Pre {
		public String timezone;
		public Integer start;
		public Integer end;
		public Integer gmtoffset;
	}
	
	@Data
	public static class Regular {
		public String timezone;
		public Integer start;
		public Integer end;
		public Integer gmtoffset;
	}
	
	@Data
	public static class Post {
		public String timezone;
		public Integer start;
		public Integer end;
		public Integer gmtoffset;
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
		public Integer firstTradeDate;
		public Integer regularMarketTime;
		public Integer gmtoffset;
		public String timezone;
		public String exchangeTimezoneName;
		public Double regularMarketPrice;
		public Double chartPreviousClose;
		public Integer priceHint;
		public CurrentTradingPeriod currentTradingPeriod;
		public String dataGranularity;
		public String range;
		public List<String> validRanges;
	}
	
	@Data
	public static class Quote {
		public List<Double> close;
	}
	
	@Data
	public static class Indicators {
		public List<Quote> quote;
	}
	
	@Data
	public static class Response {
		public Meta meta;
		public List<Long> timestamp;
		public Indicators indicators;
	}
	
	@Data
	public static class Result {
		public String symbol;
		public List<Response> response;
	}
	
	@Data
	public static class Spark {
		public List<Result> result;
		public Object error;
	}
}
