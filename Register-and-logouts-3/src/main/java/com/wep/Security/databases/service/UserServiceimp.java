package com.wep.Security.databases.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wep.Security.databases.entity.Usrr;
import com.wep.Security.databases.repository.UserRepo;

import jakarta.servlet.http.HttpSession;


@Service
public class UserServiceimp implements UserService {
	
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordcoder;

	@Override
	public Usrr saveUser(Usrr user) {
		
		String password=passwordcoder.encode(user.getPassword());
		user.setPassword(password);
		user.setRole("ROLE_USER");
		Usrr newuser=userrepo.save(user);
		
		return newuser;
	}

	@Override
	public void removeMessage() {
		
			
			HttpSession session =((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
			
			session.removeAttribute("msg");
			
		}
		
	}

	


