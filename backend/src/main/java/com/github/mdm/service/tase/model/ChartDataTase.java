package com.github.mdm.service.tase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChartDataTase {
    @JsonProperty("BaseRateInfo")
    public BaseRateInfo baseRateInfo;
    @JsonProperty("OfferingPriceData") 
    public OfferingPriceData offeringPriceData;
    @JsonProperty("PointsForDailyChart") 
    public List<PointsForDailyChart> pointsForDailyChart;
    @JsonProperty("PointsForHistoryChart") 
    public List<PointsForHistoryChart> pointsForHistoryChart;
	
    @Data
	public static class BaseRateInfo{
		@JsonProperty("DealDate")
		public Object dealDate;
		@JsonProperty("DollarBaseRate")
		public Object dollarBaseRate;
		@JsonProperty("BaseRate")
		public double baseRate;
	}
	
	@Data
	public static class OfferingPriceData{
		@JsonProperty("TradeDate")
		public Object tradeDate;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PointsForHistoryChart{
		@JsonProperty("DollarAdjustmentRate")
		public double dollarAdjustmentRate;
		@JsonProperty("AdjustmentRate")
		public double adjustmentRate;
		@JsonProperty("TradeDateGrafh")
		public Date tradeDateGrafh;
		@JsonProperty("ClosingRate")
		public Double closingRate;
		@JsonProperty("TradeDate")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
		public Date tradeDate;
		@JsonProperty("BaseRate")
		public double baseRate;
		@JsonProperty("HighRate")
		public double highRate;
		@JsonProperty("LowRate")
		public double lowRate;
		@JsonProperty("OpenRate")
		public double openRate;
		@JsonProperty("DollarMonetaryTurnOver1000")
		public double dollarMonetaryTurnOver1000;
		@JsonProperty("MonetaryTurnOver1000")
		public Object monetaryTurnOver1000;
		@JsonProperty("TurnOver1000")
		public double turnOver1000;
		@JsonProperty("EXE")
		public Object eXE;
		@JsonProperty("AdjustmentCoefficient")
		public Object adjustmentCoefficient;
		@JsonProperty("FundSellPrice")
		public double fundSellPrice;
		@JsonProperty("FundPurchasePrice")
		public double fundPurchasePrice;
		@JsonProperty("Yield")
		public double yield;
		@JsonProperty("YieldInDollar")
		public double yieldInDollar;
		@JsonProperty("ClosingRate4CSV")
		public double closingRate4CSV;
		@JsonProperty("TurnOver4CSV1000")
		public double turnOver4CSV1000;
		@JsonProperty("ShareTradingStatus")
		public Object shareTradingStatus;
		@JsonProperty("AdjustmentRateDesc")
		public Object adjustmentRateDesc;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PointsForDailyChart{
		@JsonProperty("DealTime")
		public String dealTime;
		@JsonProperty("LastRate")
		public String lastRate;
		@JsonProperty("Change")
		public double change;
		@JsonProperty("ID")
		public Object iD;
		@JsonProperty("RateType")
		public String rateType;
		@JsonProperty("TurnOverValue1000")
		public int turnOverValue1000;
		@JsonProperty("Yield")
		public int yield;
	}
}

