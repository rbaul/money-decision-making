package com.github.mdm.utils;

import com.github.mdm.service.TradingAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class SpringBeanUtils {
	private static ApplicationContext applicationContext;
	
	public SpringBeanUtils(ApplicationContext applicationContext) {
		SpringBeanUtils.applicationContext = applicationContext;
	}
	
	public static Optional<TradingAdapter> getByName(String name) {
		try {
			TradingAdapter bean = applicationContext.getBean(name, TradingAdapter.class);
			return Optional.of(bean);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
}
