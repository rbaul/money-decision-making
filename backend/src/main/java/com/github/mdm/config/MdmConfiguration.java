package com.github.mdm.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({
		MdmProperties.class
})
@Configuration
public class MdmConfiguration {
}
