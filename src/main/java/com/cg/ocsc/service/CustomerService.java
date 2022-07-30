package com.cg.ocsc.service;

import java.util.List;
import java.util.Optional;

import com.cg.ocsc.entities.Customer;
import com.cg.ocsc.entities.Issues;
import com.cg.ocsc.entities.Login;
import com.cg.ocsc.exceptions.DuplicateCustomerException;

public interface CustomerService {
	
//	public String login(Login login)throws InvalidCredentialException;
	public String registerCustomer(Customer customer)throws DuplicateCustomerException;
	public Optional<Issues> viewIssueById(int issueId);
	public Issues raiseIssue(Issues iss, String email);
	public Optional<Issues> reopenIssue(int issueId);
	public List<Issues> viewAllIssues();
	public String changePassword(Login login);
	public String forgotPassword(String customerId);
	public Optional<Customer> emailPassword(int customerId);

}
