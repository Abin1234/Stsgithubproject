package com.capgemini.dao;

import java.sql.SQLException;
import java.util.List;

import com.capgemini.model.Customer;
import com.capgemini.model.Issue;
import com.capgemini.model.Login;

public interface OperatorDao {
	public String login(Login login)throws SQLException;
	public Issue createCustomerIssue(Issue issue);
	public Issue updateCustomerIssue(Issue issue);
	public Issue closeCustomerIssue(Issue issue);
	public Customer readCustomerById(int customerId)throws SQLException;
	public List<Customer> readCustomerByName(String customerName)throws SQLException;
	public Customer readCustomerByEmail(String email)throws SQLException;
	public boolean lockCustomer(int customerId);
}
