package com.wep.Security.databases.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.wep.Security.databases.entity.Usrr;
import com.wep.Security.databases.repository.UserRepo;
import com.wep.Security.databases.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class Homecontroller {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private UserRepo userpo;
	
	@GetMapping("/index")
	public String index() {
		
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		
		return "register";
	}
	
	@GetMapping("/login")
	public String singin() {
		
		return "login";
	}
	
	@GetMapping("/user/profile")
	public String profile(Principal p,Model model) { // @formatter:off
 
		String email=p.getName();
		Usrr user=userpo.findByEmail(email);
		model.addAttribute("user",user);
		
		
		
		
		return "profile";
	}
	
	@GetMapping("/user/home")
	public String home() {
		
		return "home";
	}
    @PostMapping("/saveuser")
	public String saveUser(@ModelAttribute Usrr user,HttpSession session) {
    	//System.out.print(user);
		
    	Usrr usr=userservice.saveUser(user);
    	
    	if(usr!= null) {
    		//System.out.print("save sucess");
    		session.setAttribute("msg", "Register successully");
    	}
    	else {
    		//System.out.print("Not save");
    		session.setAttribute("msg", "somethin worng");
    	}
		
		return "redirect:/register";
	}
}
