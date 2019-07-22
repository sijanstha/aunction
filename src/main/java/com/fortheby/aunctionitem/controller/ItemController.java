package com.fortheby.aunctionitem.controller;

import com.fortheby.admin.serviceimpl.CategoryServiceImpl;
import com.fortheby.aunctionitem.entity.Item;
import com.fortheby.aunctionitem.repo.BidItemRepo;
import com.fortheby.aunctionitem.repo.ItemRepository;
import com.fortheby.aunctionitem.serviceimpl.ItemServiceImpl;
import com.fortheby.login.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private CategoryServiceImpl categoryService;
	@Autowired
	private ItemServiceImpl itemServiceImpl;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private BidItemRepo bidItemRepo;

	@GetMapping("/show-items")
	public ModelAndView showAllItems(Principal principal) {
		return itemServiceImpl.findAllItemsByLoggedUser(principal);
	}
	
	@GetMapping("/search/")
	public ModelAndView searchItem(Principal principal, HttpServletRequest request) {
		String searchItem = request.getParameter("searchQuery");
		System.out.println("search-> "+searchItem);
		if(searchItem == null || searchItem.isEmpty()) {
			return itemServiceImpl.findAllItemsByLoggedUser(principal);
		}
		return itemServiceImpl.searchAllItemsByLoggedUser(principal, searchItem);
	}
	
	@GetMapping(value = "/{itemId}", produces="application/json")
	@ResponseBody
	public Item getItemInfo(@PathVariable int itemId) {
		return itemServiceImpl.findByItemId(itemId);
	}

	@GetMapping("/add-item/{catId}")
	public ModelAndView addItem(ModelAndView modelAndView, @PathVariable("catId") int categoryId, Principal principal) {
		modelAndView.addObject("item", new Item());
		modelAndView.addObject("catId", categoryId);
		modelAndView.addObject("role", userService.getCurrentUser(principal).getRole());
		modelAndView.addObject("catList", categoryService.getAllCategories());
		modelAndView.setViewName("add-item");
		return modelAndView;
	}

	@PostMapping("/add-item/save")
	public String processAddItem(Principal principal, @Valid @ModelAttribute Item item,
			HttpServletRequest request) {
		int categoryId = Integer.parseInt(request.getParameter("catId"));

		item.setCategory(categoryService.findById(categoryId));
		item.setUser(userService.getCurrentUser(principal));
		item.setImagePath(request.getParameter("imagePath"));
		
		itemServiceImpl.saveItem(item);
		return "redirect:/item/show-items";
	}

	@GetMapping("/edit-item/{itemId}")
	public ModelAndView editItem(ModelAndView modelAndView, @PathVariable("itemId") int itemId) {
		Item item = itemServiceImpl.findByItemId(itemId);
		modelAndView.addObject("item", item);
		modelAndView.addObject("catList", categoryService.getAllCategories());
		modelAndView.setViewName("edit-item");
		return modelAndView;
	}

	@PostMapping("/update-item/{itemId}")
	public String processEditItem(@PathVariable("itemId") int itemId, Principal principal, @Valid Item item,
			HttpServletRequest request) {
		int catId = Integer.parseInt(request.getParameter("catId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		item.setItemLotNumber(itemId);
		item.setCategory(categoryService.findById(catId));
		item.setUser(userService.getUserByUserId(userId));
		itemRepo.save(item);
		return "redirect:/item/show-items";
	}

	@GetMapping("/delete-item/{itemId}")
	public String processDeleteItem(@PathVariable("itemId") int itemId) {
		itemRepo.deleteByItemLotNumber(itemId);
		return "redirect:/item/show-items";
	}
	
	
}
