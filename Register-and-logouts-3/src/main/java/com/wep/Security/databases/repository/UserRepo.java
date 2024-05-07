package com.wep.Security.databases.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.wep.Security.databases.entity.Usrr;

public interface UserRepo extends JpaRepository<Usrr,Integer> {
	
	public Usrr findByEmail(String email);

}
