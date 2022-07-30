package com.cg.ocsc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ocsc.entities.Login;

@Repository
public interface LoginDao extends JpaRepository<Login, Integer>{
	public Login findByUsernameAndPassword(String username, String password);
}
