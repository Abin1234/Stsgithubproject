package com.capgemini.dao;

import java.sql.SQLException;
import java.util.List;

import com.capgemini.model.Customer;
import com.capgemini.model.Issue;
import com.capgemini.model.Login;

public interface CustomerDao {
	public String login(Login login)throws SQLException;
	public String createCustomer(Customer customer)throws SQLException;
	public Issue readIssueById(int issueId);
	public Issue reopenIssue(int issueId);
	public List<Issue> readAllIssues();
	public String changePassword(Login login);
	public String forgotPassword(int customerId);
}
