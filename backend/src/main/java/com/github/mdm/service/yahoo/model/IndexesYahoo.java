package com.github.mdm.service.yahoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexesYahoo {
	public Finance finance;
	
	@Data
	public static class Quote {
		public String symbol;
	}
	
	@Data
	public static class Result {
		public int count;
		public List<Quote> quotes;
		public long jobTimestamp;
		public long startInterval;
	}
	
	@Data
	public static class Finance {
		public List<Result> result;
		public Object error;
	}
}
