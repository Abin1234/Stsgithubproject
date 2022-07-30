package com.cg.ocsc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ocsc.entities.Operator;

@Repository
public interface OperatorDao extends JpaRepository<Operator, Integer>{

}
