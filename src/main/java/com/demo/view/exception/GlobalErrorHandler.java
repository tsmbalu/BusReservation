package com.demo.view.exception;


import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {
	
	@ExceptionHandler(value = BindException.class)
	public String errorPage(Model model, BindException e) {
		BindingResult bindingResult = e.getBindingResult();
		if(bindingResult.hasFieldErrors()) {
			model.addAttribute("errorMessage", bindingResult.getFieldError().getDefaultMessage());
		} else if(bindingResult.hasGlobalErrors()){
			model.addAttribute("errorMessage", bindingResult.getGlobalError().getDefaultMessage());
		}
		return "errorPage";
	}

	@ExceptionHandler(value = Exception.class)
	public String errorPage(Model model, Exception e) {
		model.addAttribute("errorMessage", e.getMessage());
		return "errorPage";
	}
}
