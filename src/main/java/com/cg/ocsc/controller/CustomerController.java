package com.cg.ocsc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ocsc.entities.Customer;
import com.cg.ocsc.entities.Issues;
import com.cg.ocsc.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService cusSer;	
	
	@RequestMapping(value= "/customer/register", method= RequestMethod.POST)
	public String registerCustomer(@RequestBody Customer newcus)
	{
		return cusSer.registerCustomer(newcus);
	}
	
/*	@GetMapping("/customer/changePswd/{email}/{password}")
	public String ChangePassword(@PathVariable String email, @PathVariable String password)
	{
		Login log = new Login();
		log.setUsername(email);
		log.setPassword(password);
		String Chngpswd = cusSer.changePassword(log);
		return Chngpswd;
	}*/
	
	@GetMapping("/customer/forgotPswd/{email}")
	public String ForgotPassWord(@PathVariable String email)
	{
		String forgotpswd = cusSer.forgotPassword(email);
		return forgotpswd;
	}
	
	@GetMapping("/customer/emailPswd/{id}")
	public Customer EmailPassword(@PathVariable int id)
	{
		Optional<Customer> cus = cusSer.emailPassword(id);
		if(!cus.isPresent())
        {
             return new Customer();        // returns empty Customer object
        }
        else
            return cus.get(); 
	}
	
	@RequestMapping(value= "/customer/issRaise/{email}", method= RequestMethod.POST)
	public Issues raiseIssue(@RequestBody Issues newiss, @PathVariable String email){       
	        return cusSer.raiseIssue(newiss, email);
	}
	
	@GetMapping("/customer/reopen/{id}")
	public Issues reopenIssue(@PathVariable int id)
	{
		Optional<Issues> iss = cusSer.reopenIssue(id);
		if(!iss.isPresent())
        {
             return new Issues();        // returns empty Issue object
        }
        else
            return iss.get();  
	}
	
	@GetMapping("/customer/viewIssue/{id}")
	public Issues viewIssueById(@PathVariable int id)
	{
		Optional<Issues> iss = cusSer.viewIssueById(id);
		if(!iss.isPresent())
        {
             return new Issues();        // returns empty Issue object
        }
        else
            return iss.get(); 
	}
	
	@GetMapping("/customer/allissue")
	public List<Issues> viewAllIssues()
	{
		return cusSer.viewAllIssues();
	}
	
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	     
	        ex.getBindingResult().getFieldErrors().forEach(error -> 
	            errors.put(error.getField(), error.getDefaultMessage()));
	         
	        return errors;
	    }

}
