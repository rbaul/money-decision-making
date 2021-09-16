package com.github.mdm.service.tase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IndexDetailsTase {
    @JsonProperty("Description")
    public String description;
    @JsonProperty("BaseRate") 
    public double baseRate;
    @JsonProperty("OpenRate") 
    public double openRate;
    @JsonProperty("HighRate") 
    public double highRate;
    @JsonProperty("LowRate") 
    public double lowRate;
    @JsonProperty("MonthYield") 
    public double monthYield;
    @JsonProperty("AnnualYield") 
    public double annualYield;
    @JsonProperty("Turnover") 
    public double turnover;
    @JsonProperty("MarketValue") 
    public double marketValue;
    @JsonProperty("MarketValueDate") 
    public String marketValueDate;
    @JsonProperty("IsAssetValue") 
    public boolean isAssetValue;
    @JsonProperty("IsComponentsNextTradeDate") 
    public boolean isComponentsNextTradeDate;
    @JsonProperty("IsOtcLoaded") 
    public boolean isOtcLoaded;
    @JsonProperty("TradeDate") 
    public String tradeDate;
    @JsonProperty("TradeTime") 
    public String tradeTime;
    @JsonProperty("Name") 
    public String name;
    @JsonProperty("Id") 
    public String id;
    @JsonProperty("LastRate") 
    public double lastRate;
    @JsonProperty("Change") 
    public double change;
    @JsonProperty("Gainers") 
    public Object gainers;
    @JsonProperty("Decliners") 
    public Object decliners;
    @JsonProperty("NoChanges") 
    public Object noChanges;
    @JsonProperty("TradingStage") 
    public Object tradingStage;
    @JsonProperty("TradingStageDesc") 
    public Object tradingStageDesc;
    @JsonProperty("TradingStageMob") 
    public Object tradingStageMob;
    @JsonProperty("InDay") 
    public int inDay;
    @JsonProperty("IndexCategoryType") 
    public String indexCategoryType;
    @JsonProperty("CategoryName") 
    public Object categoryName;
    @JsonProperty("IsRezef") 
    public int isRezef;
    @JsonProperty("MarketOpen") 
    public boolean marketOpen;
}