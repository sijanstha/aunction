package com.fortheby.login.controller;

import com.fortheby.login.UserRepository;
import com.fortheby.login.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@Autowired 
	BCryptPasswordEncoder b;
	@Autowired
	UserRepository userRepo;
	@GetMapping("/login")
	public ModelAndView showLogin(ModelAndView modelAndView) {
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@GetMapping("/add-initial-user")
	public void add() {
		User admin = new User();
		admin.setUsername("admin");
		admin.setPassword(b.encode("admin"));
		admin.setEmail("admin@admin.com");
		admin.setEnabled(true);
		admin.setRole("ROLE_ADMIN");
		userRepo.save(admin);
		
		User seller = new User();
		seller.setUsername("freddy");
		seller.setPassword(b.encode("freddy"));
		seller.setEmail("freddy@gmail.com");
		seller.setEnabled(true);
		seller.setRole("ROLE_SELLER");
		userRepo.save(seller);
		
		User user = new User();
		user.setUsername("purna");
		user.setPassword(b.encode("purna"));
		user.setEmail("purna@gmail.com");
		user.setEnabled(true);
		user.setRole("ROLE_USER");
		userRepo.save(user);
	}
}
