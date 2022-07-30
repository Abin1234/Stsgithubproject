package com.cg.ocsc.service;

import java.util.List;
import java.util.Optional;

import com.cg.ocsc.entities.Customer;
import com.cg.ocsc.entities.Issues;
import com.cg.ocsc.entities.Login;
import com.cg.ocsc.exceptions.CustomerNotFoundException;
import com.cg.ocsc.exceptions.InvalidCredentialException;
import com.cg.ocsc.exceptions.IssueNotFoundException;

public interface OperatorService {
	
	public Issues createCustomerIssue(Issues issue);
	public Issues updateCustomerIssue(Issues issue);
	public Optional<Issues> getIssue(int issueId);
	public String closeCustomerIssue(Issues issue) ;
	public Optional<Customer> findCustomerById(int customerId);
	public List<Customer> findCustomerByName(String customerName);
	public Customer findCustomerByEmail(String email) ;
	public String sendModificationEmailToCustomer(int customerId, int issueId)throws CustomerNotFoundException, IssueNotFoundException;
	public String sendIntimationEmailToCustomer(int customerId, int issueId) throws CustomerNotFoundException, IssueNotFoundException;
	public String login(Login login) throws InvalidCredentialException;

}
