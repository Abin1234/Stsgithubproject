package com.cg.ocsc.service;

import java.util.List;
import java.util.Optional;

import com.cg.ocsc.entities.Department;
import com.cg.ocsc.entities.Operator;
import com.cg.ocsc.exceptions.DepartmentNotFoundException;
import com.cg.ocsc.exceptions.OperatorNotFoundException;

public interface AdminService {
	public Department addDepartment(Department department);
	public void removeDepartment(int departmentId)throws DepartmentNotFoundException;
	public Department modifyDepartment(Department department);
	public Optional<Department> findDepartmentById(int departmentId)throws DepartmentNotFoundException;
	public Operator addOperator(Operator operator);
	public void removeOperator(int operatorId)throws OperatorNotFoundException;
	public Operator modifyOperator(Operator operator);
	public Optional<Operator> findOperatorById(int operatorId)throws OperatorNotFoundException;
	public List<Operator> findAllOperators();

}
