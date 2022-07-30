package com.capgemini.service;

import java.util.List;

import com.capgemini.exception.DuplicateCustomerException;
import com.capgemini.exception.InvalidCredintialException;
import com.capgemini.model.Customer;
import com.capgemini.model.Issue;
import com.capgemini.model.Login;

public interface CustomerService {
	public String login(Login login)throws InvalidCredintialException;
	public String registerCustomer(Customer customer)throws DuplicateCustomerException;
	public Issue viewIssueById(int issueId);
	public Issue reopenIssue(int issueId);
	public List<Issue> viewAllIssues();
	public String changePassword(Login login);
	public String forgotPassword(int customerId);
	public Customer emailPassword(int customerId);
}
