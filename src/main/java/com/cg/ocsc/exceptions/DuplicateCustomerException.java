package com.cg.ocsc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.IM_USED)
public class DuplicateCustomerException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DuplicateCustomerException() {
		super();
	}

	public DuplicateCustomerException(String message) {
		super(message);
	}
	
	
}
