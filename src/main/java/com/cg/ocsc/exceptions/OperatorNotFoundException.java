package com.cg.ocsc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OperatorNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public OperatorNotFoundException() {
    	
    }

    public OperatorNotFoundException(String message){
        super(message);
    }
    public Object getBindingResult() {
		// TODO Auto-generated method stub
		return null;
	}
    
}

