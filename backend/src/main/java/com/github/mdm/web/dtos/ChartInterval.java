package com.github.mdm.web.dtos;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ChartInterval {
	_1_DAY("1d");
	
	@Getter(onMethod_ = @JsonValue)
	private final String representString;
}
