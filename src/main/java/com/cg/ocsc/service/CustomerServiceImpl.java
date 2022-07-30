package com.cg.ocsc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ocsc.entities.Customer;
import com.cg.ocsc.entities.Issues;
import com.cg.ocsc.entities.Login;
import com.cg.ocsc.exceptions.DuplicateCustomerException;
import com.cg.ocsc.repositories.CustomerDao;
import com.cg.ocsc.repositories.IssuesDao;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDao cusRep;
	
	@Autowired
	private IssuesDao issRepo;
	
	Issues issue;
	Customer cus;
	
/*	@Override
	public String login(Login login) throws InvalidCredentialException {
		
		cus = (Customer) cusRepo.findByEmail(login.getUsername());
		if(cus == null)
		{
			throw new InvalidCredentialException("UserName is Invalid : "+login.getUsername()); 
		}
		else if(login.getUsername().equals(cus.getEmail()) && login.getPassword().equals(cus.getPassword()) && login.getType().equals(UserType.CUSTOMER.toString()))
		{
			return "Welcome to Our Portal";
		}
		else
		{
			throw new InvalidCredentialException("Failed to Login with"+" "+login.getUsername()+" "+"or Password "+"or UserType Case is Invalid");
		}
	}*/


	@Override
	public String registerCustomer(Customer customer) throws DuplicateCustomerException {
		String email=customer.getEmail();
		cus=cusRep.findByEmail(email);
		if(cus==null)
		{
			 cusRep.save(customer);
			 return "Successfully Registered";
		}
		else
		{
			throw new DuplicateCustomerException("User name already exists"+" "+customer.getEmail());
		}
		
	}

	@Override
	public Optional<Issues> viewIssueById(int issueId) {
		
		return issRepo.findById(issueId);
	}

	@Override
	public Issues raiseIssue(Issues iss, String email) {
		
		cus =cusRep.findByEmail(email);
		if(cus!=null)
		{
			iss.setCustomer(cus);
			issRepo.save(iss);
			return iss;
		}
		else
		{
			return new Issues();
		}
		
	}
	
	@Override
	public Optional<Issues> reopenIssue(int issueId) {
		
		Optional<Issues> issue = issRepo.findById(issueId);
		return issue;//Contact Operator
		
	}

	@Override
	public List<Issues> viewAllIssues() {
		
		return issRepo.findAll();
	}

	@Override
	public String changePassword(Login login) {
		
		cus =cusRep.findByEmail(login.getUsername());
		if(login.getUsername().equals(cus.getEmail()))
		{
			cus.setPassword(login.getPassword());
			cusRep.save(cus);
			return "Successfully Changed";
		}
		else
		{
			return "Not Valid User";
		}
	}

	@Override
	public String forgotPassword(String email) {
		
		cus = cusRep.findByEmail(email);
		if(email.equals(cus.getEmail()))
		{
			return "Your Old Password is : "+cus.getPassword();
		}
		return "Not Valid User";
	}

	@Override
	public Optional<Customer> emailPassword(int customerId) {
		
		return cusRep.findById(customerId);
	}


}
