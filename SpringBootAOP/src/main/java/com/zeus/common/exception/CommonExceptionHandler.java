package com.zeus.common.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public String handle(Exception e, Model model) {
		log.info(e.toString());
		model.addAttribute("exception", e); 
		return "error/errorCommon"; 
	}
}