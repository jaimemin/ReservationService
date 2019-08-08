package com.nts.reserve.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private static final String DEFAULT_ERROR_VIEW = "commonError";

	/*
	 * 모든 에러를 동일하게 처리할 것이기 때문에 Exception.class
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception exception) {
		LOGGER.error("error message {}. Details:", exception.getMessage(), exception);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(DEFAULT_ERROR_VIEW);

		return modelAndView;
	}

}
