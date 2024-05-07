package com.wep.Security.databases.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.wep.Security.databases.entity.Usrr;
import com.wep.Security.databases.repository.UserRepo;


@Component
public class CustemerUserDetaliService implements UserDetailsService {
	
	@Autowired
	private UserRepo userpo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usrr user=userpo.findByEmail(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("user not fount");
		}
		else {
			return new CustemerUser(user);
		}
		
	}

}
