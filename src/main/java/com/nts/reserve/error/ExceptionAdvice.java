package com.nts.reserve.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);
	
	/*
	 * 모든 에러를 동일하게 처리할 것이기 때문에 Exception.class
	 */
	@ExceptionHandler(Exception.class)
	public String handleException(Model model, Exception exception) {
		LOGGER.error("error message {}. Details:",
				exception.getMessage(), exception.getStackTrace());
		
		return "error/exception";
	}
}
