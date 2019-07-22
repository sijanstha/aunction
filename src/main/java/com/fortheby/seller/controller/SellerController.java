package com.fortheby.seller.controller;

import com.fortheby.aunctionitem.serviceimpl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/seller")
public class SellerController {

	
	@Autowired
	private ItemServiceImpl itemServiceImpl;

	@GetMapping("")
	public ModelAndView goHome(ModelAndView modelAndView, Principal principal) {
		return itemServiceImpl.findAllItemsByLoggedUser(principal);
	}
	
}
