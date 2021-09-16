package com.github.mdm.service.yahoo.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;

@FieldNameConstants
@RequiredArgsConstructor
public enum Range {
	_1_DAY("1d"),
	_5_DAY("5d"),
	_1_MONTH("1mo"),
	_3_MONTH("3mo"),
	_6_MONTH("6mo"),
	_1_YEAR("1y"),
	_2_YEAR("2y"),
	_5_YEAR("5y"),
	_10_YEAR("10y"),
	YTD("ytd"),
	MAX("max")
	;
	
	@Getter(onMethod_ = @JsonValue)
	private final String representString;
	
}
