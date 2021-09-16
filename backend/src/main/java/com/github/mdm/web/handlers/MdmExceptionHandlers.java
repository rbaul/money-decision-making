package com.github.mdm.web.handlers;

import com.github.mdm.exceptions.MdmException;
import com.github.mdm.web.dtos.errors.ErrorDto;
import com.github.rozidan.springboot.logger.Loggable;
import org.springframework.boot.logging.LogLevel;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Loggable(entered = true, value = LogLevel.ERROR)
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class MdmExceptionHandlers {
	
	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler(MdmException.class)
	public ErrorDto handleMdmServerException(MdmException ex) {
		return ErrorDto.builder()
				.errorCode(ex.getMessage())
				.message(ex.getMessage())
				.build();
	}
	
}
