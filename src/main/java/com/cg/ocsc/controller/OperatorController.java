package com.cg.ocsc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cg.ocsc.entities.Customer;
import com.cg.ocsc.entities.Issues;
import com.cg.ocsc.entities.Login;
import com.cg.ocsc.exceptions.CustomerNotFoundException;
import com.cg.ocsc.exceptions.InvalidCredentialException;
import com.cg.ocsc.exceptions.IssueNotFoundException;
import com.cg.ocsc.service.OperatorService;

public class OperatorController {
	
	@Autowired
	private OperatorService opService;
	
	@GetMapping("/")
	public String Home() {
		return "YOU JUST GOT YOUR MISSING QUOTIENT OF HAPPINESS";
	}
	
	/*@PostMapping("/login")
	public String login(@RequestBody Login login) throws InvalidCredentialException{
		return opService.login(login);
	}*/
	
	@PostMapping("/issue/create")
	public Issues createCustomerIssue(@RequestBody Issues issue) {
		return opService.createCustomerIssue(issue);
	}
	
	@GetMapping("/issue/byId/{id}")
	public ResponseEntity<Issues> getCustomerIssue(@PathVariable(value="id") int id) throws IssueNotFoundException{
		Issues iss=opService.getIssue(id).orElseThrow(()->new IssueNotFoundException("Issues not found for this id: "+id));
		return ResponseEntity.ok().body(iss);
	}
	
	@PutMapping("/updateissue/{id}")
	public ResponseEntity<Issues> updateCustomerIssue(@RequestBody Issues updIssue, @PathVariable int id) throws IssueNotFoundException {
		opService.getIssue(id).orElseThrow(()-> new IssueNotFoundException("Issue Not found for this id : "+id));
		updIssue.setIssueId(id);
		final Issues issues=opService.updateCustomerIssue(updIssue);
		return ResponseEntity.ok().body(issues);
	}
	
	@RequestMapping(value="/closeissue/{id}", method= RequestMethod.GET)
	public String closeCustomerIssue(@PathVariable int id) throws IssueNotFoundException {
	    Issues issue=opService.getIssue(id).orElseThrow(()-> new IssueNotFoundException("Issue Not found for this id : "+id));
	    issue.setIssueStatus("Closed");
	    return opService.closeCustomerIssue(issue);
	}
	
	@GetMapping("/fetchcustomerbyid/{id}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable(value = "id") int id) throws CustomerNotFoundException {
		 Customer cust=opService.findCustomerById(id).orElseThrow(()-> new CustomerNotFoundException("Customer NOt found "));
		 return ResponseEntity.ok().body(cust);
	}
	
	@RequestMapping(value ="/fetchcustomerbyname/{name}", method = RequestMethod.GET)
	public List<Customer> findCustomerByName(@PathVariable (value="name") String name) throws CustomerNotFoundException{
		List<Customer> cust= opService.findCustomerByName(name);
		if(cust.isEmpty()) {
			throw new CustomerNotFoundException("Customer not found for this name : " + name);
		}
		else {
			return cust;
		}
	}
	
	@RequestMapping(value ="/fetchcustomerbymail/{email}", method = RequestMethod.GET)
	public ResponseEntity<Customer> findCustomerByEmail(@PathVariable(value="email") String email) throws CustomerNotFoundException {
		Customer cust=opService.findCustomerByEmail(email);
		if (cust==null) {
			throw new CustomerNotFoundException("Customer not found for this email : " + email);
		}
		return ResponseEntity.ok().body(cust);
	}
	
	@GetMapping("/sendIntimationEmail/{custId}/{issueId}")
	public String sendIntimationEmailToCustomer(@PathVariable(value="custId") int customerId,@PathVariable(value="issueId") int issueId) throws CustomerNotFoundException, IssueNotFoundException {
		return opService.sendIntimationEmailToCustomer(customerId, issueId);
	}
	
	@GetMapping("/sendModificationEmail/{custId}/{issueId}")
	public String sendModificationEmailToCustomer(@PathVariable(value="custId") int customerId,@PathVariable(value="issueId") int issueId) throws CustomerNotFoundException, IssueNotFoundException {
		return opService.sendModificationEmailToCustomer(customerId, issueId);
	}

}
