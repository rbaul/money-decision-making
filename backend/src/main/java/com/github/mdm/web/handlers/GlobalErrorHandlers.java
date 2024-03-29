package com.github.mdm.web.handlers;

import com.github.mdm.web.dtos.errors.ErrorCodes;
import com.github.mdm.web.dtos.errors.ErrorDto;
import com.github.mdm.web.dtos.errors.HttpMediaTypeErrorDto;
import com.github.mdm.web.dtos.errors.HttpRequestMethodErrorDto;
import com.github.rozidan.springboot.logger.Loggable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Collections;

/**
 * It is recommended to replace the messages with those
 * that do not reveal details about the code.
 */
@Slf4j
@Loggable(entered = true, value = LogLevel.ERROR)
@RestControllerAdvice
public class GlobalErrorHandlers {
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ErrorDto handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		return ErrorDto.builder()
				.errorCode(ErrorCodes.NOT_FOUND.toString())
				.message(ex.getLocalizedMessage())
				.build();
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ErrorDto handleNoHandlerFoundException(NoHandlerFoundException ex) {
		return ErrorDto.builder()
				.errorCode(ErrorCodes.NOT_FOUND.toString())
				.message(ex.getLocalizedMessage())
				.build();
	}
	
	@Loggable
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ErrorDto handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		return ErrorDto.builder()
				.errorCode(ErrorCodes.METHOD_NOT_ALLOWED.toString())
				.errors(Collections.singleton(HttpRequestMethodErrorDto.builder()
						.actualMethod(ex.getMethod())
						.supportedMethods(ex.getSupportedHttpMethods())
						.build()))
				.message(ex.getLocalizedMessage())
				.build();
	}
	
	@ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ErrorDto handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
		return ErrorDto.builder()
				.errorCode(ErrorCodes.HTTP_MEDIA_TYPE_NOT_SUPPORTED.toString())
				.errors(Collections.singleton(HttpMediaTypeErrorDto.builder()
						.mediaType(ex.getContentType())
						.build()))
				.message(ex.getLocalizedMessage())
				.build();
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorDto handleGlobalError(Exception ex) {
		log.error("Global error handler exception: ", ex);
		return ErrorDto.builder()
				.errorCode(ErrorCodes.UNKNOWN.toString())
				.message(ex.getLocalizedMessage())
				.build();
	}
}