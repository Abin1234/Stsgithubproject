package com.capgemini.model;

import java.util.Date;

public class Call {
	private int callId;
	private Date callDate;
	private double callDuration;
	private String phoneNumber;
	
	private Customer customer;
	private Issue issue;
	private Operator receivedBy;
}
