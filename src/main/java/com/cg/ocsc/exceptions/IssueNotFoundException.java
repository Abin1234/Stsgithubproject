package com.cg.ocsc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class IssueNotFoundException extends Exception{
	
	private static final long serialVersionUID=1L;
	
	public IssueNotFoundException(String message) {
		super(message);
	}

}
