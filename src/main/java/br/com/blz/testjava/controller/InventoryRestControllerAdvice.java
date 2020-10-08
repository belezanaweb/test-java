package br.com.blz.testjava.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.RequestContextUtils;

@RestControllerAdvice
public class InventoryRestControllerAdvice {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(RepositoryConstraintViolationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST) 
	public List<String> validationException(HttpServletRequest req, RepositoryConstraintViolationException ex) {
		return ex.getErrors().getAllErrors()
			.stream()
			.map(e -> messageSource.getMessage(e.getCode(), e.getArguments(), e.getDefaultMessage(), RequestContextUtils.getLocaleResolver(req).resolveLocale(req)))
			.collect(Collectors.toList());
	}
}
