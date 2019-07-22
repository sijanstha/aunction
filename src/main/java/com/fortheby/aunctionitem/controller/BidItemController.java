package com.fortheby.aunctionitem.controller;

import com.fortheby.aunctionitem.entity.BidItem;
import com.fortheby.aunctionitem.repo.BidItemRepo;
import com.fortheby.exception.CustomException;
import com.fortheby.login.entity.User;
import com.fortheby.login.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/bid-item")
public class BidItemController {

	@Autowired
	private BidItemRepo bidItemRepo;

	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/save")
	public String bidItem(@ModelAttribute BidItem bidItem, Principal principal) {
		User user = userService.getCurrentUser(principal);
		bidItem.setUserId(user.getUserId());
		System.out.println(bidItem);
		bidItemRepo.save(bidItem);
		return "redirect:/user";
	}

	@RequestMapping(path = "/status/{itemId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public BidItem bidItemTest(@PathVariable int itemId, Principal principal) {
		BidItem bidItem = bidItemRepo.findByItemIdAndUserId(itemId, userService.getCurrentUser(principal).getUserId());

		if (bidItem == null) {
			throw new CustomException("Not Found", HttpStatus.NOT_FOUND);
		}
		return bidItem;
	}
}
