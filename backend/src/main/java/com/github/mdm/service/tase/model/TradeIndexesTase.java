package com.github.mdm.service.tase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TradeIndexesTase {
    @JsonProperty("TradeDate")
    public String tradeDate;
    @JsonProperty("TradeTime") 
    public String tradeTime;
    @JsonProperty("Name") 
    public String name;
    @JsonProperty("Id") 
    public String id;
    @JsonProperty("LastRate") 
    public Double lastRate;
    @JsonProperty("Change") 
    public Double change;
    @JsonProperty("Turnover") 
    public Double turnover;
    @JsonProperty("Gainers") 
    public Integer gainers;
    @JsonProperty("Decliners") 
    public Integer decliners;
    @JsonProperty("NoChanges") 
    public Integer noChanges;
    @JsonProperty("TradingStage") 
    public Object tradingStage;
    @JsonProperty("TradingStageDesc") 
    public Object tradingStageDesc;
    @JsonProperty("TradingStageMob") 
    public Object tradingStageMob;
    @JsonProperty("InDay") 
    public Integer inDay;
    @JsonProperty("IndexCategoryType") 
    public String indexCategoryType;
    @JsonProperty("CategoryName") 
    public Object categoryName;
    @JsonProperty("IsRezef") 
    public Integer isRezef;
    @JsonProperty("MarketOpen") 
    public Boolean marketOpen;
}