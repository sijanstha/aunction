package com.fortheby.admin.controller;

import com.fortheby.admin.serviceimpl.CategoryServiceImpl;
import com.fortheby.aunctionitem.serviceimpl.ItemServiceImpl;
import com.fortheby.login.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;

@Controller
public class AdminController {

	@Autowired
	private ItemServiceImpl itemService;
	@Autowired
	private CategoryServiceImpl categoryService;
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@GetMapping("/admin")
	public ModelAndView showWelcome(ModelAndView modelAndView, Principal principal) {
		
		Map<String, String> userCounts = userServiceImpl.findUserCount();
		
		modelAndView.addObject("itemCount", itemService.findAllItemsCount());
		modelAndView.addObject("totalRevenue", "$"+itemService.findTotalBidRevenue());
		modelAndView.addObject("sellerCount", userCounts.get("ROLE_SELLER"));
		modelAndView.addObject("buyerCount", userCounts.get("ROLE_USER"));
		modelAndView.addObject("catList", categoryService.getAllCategories());
		modelAndView.addObject("username", principal.getName());
		modelAndView.addObject("role", "ROLE_ADMIN");
		modelAndView.setViewName("admin/index");
		return modelAndView;
	}
}
