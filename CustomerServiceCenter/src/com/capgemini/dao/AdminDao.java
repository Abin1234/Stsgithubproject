package com.capgemini.dao;

import java.sql.SQLException;
import java.util.List;

import com.capgemini.model.Department;
import com.capgemini.model.Operator;

public interface AdminDao {
	public int createDepartment(Department department);
	public int deleteDepartment(int departmentId)throws SQLException;
	public Department updateDepartment(Department department);
	public Department readDepartmentById(int departmentId)throws SQLException;
	public int createOperator(Operator operator);
	public int deleteOperator(int operatorId)throws SQLException;
	public Operator updateOperator(Operator operator);
	public Operator readOperatorById(int operatorId)throws SQLException;
	public List<Operator> readAllOperators();
}
