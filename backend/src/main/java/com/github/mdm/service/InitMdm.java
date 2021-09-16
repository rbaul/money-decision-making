package com.github.mdm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitMdm implements ApplicationRunner {
	
	private final TradingFactory tradingFactory;
	
	@Override
	public void run(ApplicationArguments args) {
		log.info("Initialize MDM data");
		tradingFactory.init();
	}
}
