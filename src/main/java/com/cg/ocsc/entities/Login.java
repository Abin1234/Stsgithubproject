package com.cg.ocsc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {
	@Id
	private int operatorId;
	private String username;
	private String password;
	private boolean isActive;
	public Login(int operatorId, String username, String password, boolean isActive) {
		super();
		this.operatorId = operatorId;
		this.username = username;
		this.password = password;
		this.isActive = isActive;
	}
	
	public Login() {
		super();
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
