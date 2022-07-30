package com.capgemini.service;

import java.util.List;

import com.capgemini.exception.DepartmentNotFoundException;
import com.capgemini.exception.OperatorNotFoundException;
import com.capgemini.model.Department;
import com.capgemini.model.Operator;

public interface AdminService {
	public boolean addDepartment(Department department);
	public boolean removeDepartment(int departmentId)throws DepartmentNotFoundException;
	public Department modifyDepartment(Department department);
	public Department findDepartmentById(int departmentId)throws DepartmentNotFoundException;
	public boolean addOperator(Operator operator);
	public boolean removeOperator(int operatorId)throws OperatorNotFoundException;
	public Operator modifyOperator(Operator operator);
	public Operator findOperatorById(int operatorId)throws OperatorNotFoundException;
	public List<Operator> findAllOperators();
}
