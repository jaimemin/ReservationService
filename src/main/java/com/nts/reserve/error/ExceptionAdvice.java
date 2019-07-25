package com.nts.reserve.error;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);
	
	@ExceptionHandler(IOException.class)
	public String errorException(Model model, Exception exception) {
		LOGGER.info("@ControllerAdvice 방식 \n"
				+ "###exception: " + exception.getMessage());
		model.addAttribute("exception", exception);
		
		return "error/exception";
	}
}
