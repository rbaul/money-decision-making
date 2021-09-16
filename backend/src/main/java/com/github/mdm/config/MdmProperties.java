package com.github.mdm.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "mdm.indicators")
public class MdmProperties {
	private Set<String> defaultValues = new HashSet<>(Arrays.asList("NASDAQ Composite", "TA-35", "CBOE Volatility Index","Barrick Gold Corporation"));
}
