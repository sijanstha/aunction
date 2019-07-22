package com.fortheby.login.controller;

import com.fortheby.cmn.ApiResponse;
import com.fortheby.login.UserRepository;
import com.fortheby.login.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterUserController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	BCryptPasswordEncoder b;

	@GetMapping("/register/seller")
	public ModelAndView showSellerRegisterPage(ModelAndView modelAndView) {
		modelAndView.addObject("newuser", new User());
		modelAndView.addObject("role", "ROLE_SELLER");
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@GetMapping("/register/buyer")
	public ModelAndView showBuyerRegisterPage(ModelAndView modelAndView) {
		modelAndView.addObject("newuser", new User());
		modelAndView.addObject("role", "ROLE_USER");
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@PostMapping("/register/save")
	public ModelAndView registerUser(@ModelAttribute User user, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		user.setRole(request.getParameter("role"));
		String pass = user.getPassword();
		user.setPassword(b.encode(pass));
		user.setEnabled(true);
		userRepo.save(user);
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@GetMapping(value="/checkuser",produces="application/json")
	@ResponseBody
	public ApiResponse checkDuplicateUserName(@RequestParam String userName) {
		ApiResponse response = new ApiResponse();
		User user = userRepo.findByUsername(userName);
		if(user == null) {
			response.setStatus(0);
			response.setMessage(userName+" doesnot exist");
		}else {
			response.setStatus(100);
			response.setMessage("This username is already taken. Please use another one!");
		}
		
		return response;
	}

}
