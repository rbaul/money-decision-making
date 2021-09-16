package com.github.mdm.service.yahoo.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@FieldNameConstants
@RequiredArgsConstructor
public enum Interval {
	_1_MIN("1m"),
	_2_MIN("2m"),
	_5_MIN("5m"),
	_15_MIN("15m"),
	_30_MIN("30m"),
	_60_MIN("60m"),
	_90_MIN("90m"),
	_1_HOUR("1h"),
	_1_DAY("1d"),
	_5_DAY("5d"),
	_1_WEEK("1wk"),
	_1_MONTH("1mo"),
	_3_MONTH("3mo");
	
	@Getter(onMethod_ = @JsonValue)
	private final String representString;
	
}
