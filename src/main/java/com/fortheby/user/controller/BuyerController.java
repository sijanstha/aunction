package com.fortheby.user.controller;

import com.fortheby.admin.serviceimpl.CategoryServiceImpl;
import com.fortheby.aunctionitem.entity.BidItem;
import com.fortheby.aunctionitem.entity.Item;
import com.fortheby.aunctionitem.model.BidItemModel;
import com.fortheby.aunctionitem.repo.BidItemRepo;
import com.fortheby.aunctionitem.serviceimpl.ItemServiceImpl;
import com.fortheby.login.entity.User;
import com.fortheby.login.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller

@RequestMapping("/user")
public class BuyerController {

	@Autowired
	private ItemServiceImpl itemServiceImpl;
	@Autowired
	private BidItemRepo bidItemRepo;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private CategoryServiceImpl categoryService;

	@GetMapping("")
	public ModelAndView goHome(Principal principal) {
		return itemServiceImpl.findAllItemsByLoggedUser(principal);
	}

	
	@GetMapping("/bid/history")
	public ModelAndView userBidHistory(ModelAndView modelAndView, Principal principal) {
		User user = userService.getCurrentUser(principal);
		List<BidItem> listOfBidItemsByUser = bidItemRepo.findByUserId(user.getUserId());
		List<BidItemModel> itemBidByUser = new ArrayList<>();
		
		for(BidItem bidItem : listOfBidItemsByUser) {
			BidItemModel model = new BidItemModel();
			model.setBidAmt(bidItem.getBidAmount());
			model.setDate(bidItem.getCreatedDate());
			model.setMessage(bidItem.getMessage());
			Item item = itemServiceImpl.findByItemId(bidItem.getItemId());
			model.setItemName(item.getTitle());
			itemBidByUser.add(model);
		}
		modelAndView.addObject("catList", categoryService.getAllCategories());
		modelAndView.addObject("username", principal.getName());
		modelAndView.addObject("bidItem", new BidItem());
		modelAndView.addObject("role", user.getRole());
		modelAndView.addObject("bidItemList", itemBidByUser);
		modelAndView.setViewName("user/bid-history");
		return modelAndView;
	}

}
