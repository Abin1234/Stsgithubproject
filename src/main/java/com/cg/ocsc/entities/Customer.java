package com.cg.ocsc.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
	private int customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String password;
	private String city;
	private String fullName= firstName+" "+lastName;
	
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
	private Set<Issues> issues = new HashSet<>();
	
	public Customer(int customerId, String firstName, String lastName, String email, String mobile, String city) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.city = city;
	}
	public Customer() {
		super();
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Set<Issues> getIssues() {
		return issues;
	}
	public void setIssues(Set<Issues> issues) {
		this.issues = issues;
	}
	//the method below will add issue to customer 
		//also serves the purpose to avoid cyclic references. 
	public void addIssues(Issues issue) {
		issue.setCustomer(this);			//this will avoid nested cascade
		this.getIssues().add(issue);
	}

}
