package com.cg.ocsc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ocsc.entities.Customer;
import com.cg.ocsc.entities.Issues;
import com.cg.ocsc.entities.Login;
import com.cg.ocsc.exceptions.CustomerNotFoundException;
import com.cg.ocsc.exceptions.InvalidCredentialException;
import com.cg.ocsc.exceptions.IssueNotFoundException;
import com.cg.ocsc.repositories.CustomerDao;
import com.cg.ocsc.repositories.IssuesDao;
import com.cg.ocsc.repositories.LoginDao;

@Service
public class OperatorServiceImpl implements OperatorService {
	
	@Autowired
	private IssuesDao issueDao; 
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private LoginDao loginDao;

	@Override
	public Issues createCustomerIssue(Issues issue) {
		return issueDao.save(issue);
	}
	@Override
	public Optional<Issues> getIssue(int issueId){
		return issueDao.findById(issueId);
	}
	@Override
	public Issues updateCustomerIssue(Issues issue) {
		return issueDao.save(issue);
	}
	@Override
	public String closeCustomerIssue(Issues issue) {
		issueDao.save(issue);
		return "Status Closed";
	}
	@Override
	public Optional<Customer> findCustomerById(int customerId) {
		return customerDao.findById(customerId);
	}
	@Override
	public List<Customer> findCustomerByName(String customerName) {
		return customerDao.findByFullName(customerName);
	}
	@Override
	public Customer findCustomerByEmail(String email) {
		return customerDao.findByEmail(email);
	}
	@Override
	public String sendModificationEmailToCustomer(int customerId, int issueId) throws CustomerNotFoundException, IssueNotFoundException {
		Customer cust=customerDao.findById(customerId).orElseThrow(()->new CustomerNotFoundException("Customer not found for this id"+ customerId));
		Issues issue=issueDao.findById(issueId).orElseThrow(()-> new IssueNotFoundException("Issue not found for this id"+ issueId));
		String description=issue.getDescription();
		String status=issue.getIssueStatus();
		String name=cust.getFullName();
		return "Hello "+ name+". Your issue Id "+issueId+" has a new update - "+description+" : "+status;
	}
	@Override
	public String sendIntimationEmailToCustomer(int customerId, int issueId) throws CustomerNotFoundException, IssueNotFoundException {
		Customer cust=customerDao.findById(customerId).orElseThrow(()->new CustomerNotFoundException("Customer not found for this id"+ customerId));
		Issues issue=issueDao.findById(issueId).orElseThrow(()-> new IssueNotFoundException("Issue not found for this id"+ issueId));
		String name=cust.getFullName();
		String description=issue.getDescription();
		return "HELLO "+name+". Your issue "+description+" has been successfully created with Id "+issueId+". Your issue will be resolved within two working days.";
	}
	@Override
	public String login(Login login) throws InvalidCredentialException {
		String auUserName=login.getUsername();
		String auPassword=login.getPassword();
		Login loginObj=null;
		if(auUserName !=null && auPassword!=null) {
			loginObj=loginDao.findByUsernameAndPassword(auUserName, auPassword);
		}
		if(loginObj==null) {
			throw new InvalidCredentialException("Credentials are not valid");
		}
		return "Welcome";
	}
}
