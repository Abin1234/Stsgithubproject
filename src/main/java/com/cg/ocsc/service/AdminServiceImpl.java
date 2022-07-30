package com.cg.ocsc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ocsc.entities.Department;
import com.cg.ocsc.entities.Operator;
import com.cg.ocsc.exceptions.DepartmentNotFoundException;
import com.cg.ocsc.exceptions.OperatorNotFoundException;
import com.cg.ocsc.repositories.DepartmentDao;
import com.cg.ocsc.repositories.OperatorDao;



@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private DepartmentDao dnRepo;
	@Autowired
	private OperatorDao oprRepo;

	@Override
	public Department addDepartment(Department department) {
		return dnRepo.save(department);
	}

	@Override
	public void removeDepartment(int departmentId) throws DepartmentNotFoundException {
		dnRepo.deleteById(departmentId);

	}

	@Override
	public Department modifyDepartment(Department department) {
		return dnRepo.save(department);
	}

	@Override
	public Optional<Department> findDepartmentById(int departmentId) throws DepartmentNotFoundException {
		return dnRepo.findById(departmentId);
	}

	@Override
	public Operator addOperator(Operator operator) {
		return oprRepo.save(operator);
	}

	@Override
	public void removeOperator(int operatorId) throws OperatorNotFoundException {
		 oprRepo.deleteById(operatorId);
	}

	@Override
	public Operator modifyOperator(Operator operator) {
		return oprRepo.save(operator);
	}

	@Override
	public Optional<Operator> findOperatorById(int operatorId) throws OperatorNotFoundException {
		return oprRepo.findById(operatorId);

	}

	@Override
	public List<Operator> findAllOperators() {
		return oprRepo.findAll();
	}

	
}