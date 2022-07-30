package com.capgemini.service;

import java.util.List;

import com.capgemini.exception.CustomerNotFoundException;
import com.capgemini.exception.InvalidCredintialException;
import com.capgemini.exception.IssueNotFoundException;
import com.capgemini.model.Customer;
import com.capgemini.model.Issue;
import com.capgemini.model.Login;

public interface OperatorService {
	public String login(Login login)throws InvalidCredintialException;
	public Issue addCustomerIssue(Issue issue);
	public String sendIntimationEmailToCustomer(int customerId, int issueId)throws CustomerNotFoundException, IssueNotFoundException;
	public Issue modifyCustomerIssue(Issue issue);
	public String sendModificationEmailToCustomer(int customerId, int issueId)throws CustomerNotFoundException, IssueNotFoundException;
	public Issue closeCustomerIssue(Issue issue);
	public Customer findCustomerById(int customerId)throws CustomerNotFoundException;
	public List<Customer> findCustomerByName(String customerName)throws CustomerNotFoundException;
	public Customer findCustomerByEmail(String email)throws CustomerNotFoundException;
	public boolean lockCustomer(int customerId);
}
