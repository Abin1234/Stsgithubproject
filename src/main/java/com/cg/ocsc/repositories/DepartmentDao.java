package com.cg.ocsc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ocsc.entities.Department;

@Repository
public interface DepartmentDao extends JpaRepository<Department,Integer>{

}
