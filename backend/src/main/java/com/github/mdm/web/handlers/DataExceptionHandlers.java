package com.github.mdm.web.handlers;

import com.github.mdm.web.dtos.errors.ErrorCodes;
import com.github.mdm.web.dtos.errors.ErrorDto;
import com.github.rozidan.springboot.logger.Loggable;
import org.springframework.boot.logging.LogLevel;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * It is recommended to replace the messages with those
 * that do not reveal details about the code.
 */
@Loggable(entered = true, value = LogLevel.ERROR)
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class DataExceptionHandlers {
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ErrorDto handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
		return ErrorDto.builder()
				.errorCode(ErrorCodes.NOT_FOUND.toString())
				.message(ex.getLocalizedMessage())
				.build();
	}
	
}
