package com.fortheby.aunctionitem.serviceimpl;

import com.fortheby.admin.entity.Category;
import com.fortheby.admin.serviceimpl.CategoryServiceImpl;
import com.fortheby.aunctionitem.entity.BidItem;
import com.fortheby.aunctionitem.entity.Item;
import com.fortheby.aunctionitem.repo.BidItemRepo;
import com.fortheby.aunctionitem.repo.ItemRepository;
import com.fortheby.login.entity.User;
import com.fortheby.login.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Service
public class ItemServiceImpl {

	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private CategoryServiceImpl categoryService;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private BidItemRepo bidItemRepo;

	public long findAllItemsCount() {
		return itemRepo.count();
	}

	public String findTotalBidRevenue() {
		Object[] totalRevenue = bidItemRepo.findTotalAunctionRevenue();
		return totalRevenue[0].toString();
	}

	public ModelAndView findAllItemsByCategoryId(int categoryId, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.getCurrentUser(principal);
		Category cat = categoryService.findById(categoryId);
		if (cat == null) {
			modelAndView.addObject("error", true);
			modelAndView.addObject("errorMessage", "Not a valid category id");
			modelAndView.setViewName("404");
			return modelAndView;
		}

		List<Item> itemList = null;
		if (user.getRole().equals("ROLE_USER") || user.getRole().equals("ROLE_ADMIN")) {
			itemList = itemRepo.findAllByCategory(cat);
		} else {
			itemList = itemRepo.findAllByCategoryAndUser(cat, user);
		}
		modelAndView.addObject("catList", categoryService.getAllCategories());
		modelAndView.addObject("catName", cat.getCategoryName());
		modelAndView.addObject("bidItem", new BidItem());
		modelAndView.addObject("username", principal.getName());
		modelAndView.addObject("catId", categoryId);
		modelAndView.addObject("role", user.getRole());
		modelAndView.addObject("itemList", itemList);
		modelAndView.setViewName("category-based-items");
		return modelAndView;
	}

	public ModelAndView findAllItemsByLoggedUser(Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.getCurrentUser(principal);
		List<Item> itemList = null;

		if (user.getRole().equals("ROLE_SELLER")) {
			itemList = itemRepo.findAllByUser(user);
		} else {
			itemList = itemRepo.findAll();
		}
		modelAndView.addObject("catList", categoryService.getAllCategories());
		modelAndView.addObject("username", principal.getName());
		modelAndView.addObject("bidItem", new BidItem());
		modelAndView.addObject("role", user.getRole());
		modelAndView.addObject("itemList", itemList);
		modelAndView.setViewName("all-items");
		return modelAndView;
	}
	
	public ModelAndView searchAllItemsByLoggedUser(Principal principal, String searchQuery) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.getCurrentUser(principal);
		List<Item> itemList = null;

		if (user.getRole().equals("ROLE_SELLER")) {
			itemList = itemRepo.searchItemByUserAndItemName(user.getUserId(), searchQuery+"%");
		} else {
			itemList = itemRepo.searchItemByItemName(searchQuery+"%");
		}
		modelAndView.addObject("catList", categoryService.getAllCategories());
		modelAndView.addObject("username", principal.getName());
		modelAndView.addObject("bidItem", new BidItem());
		modelAndView.addObject("role", user.getRole());
		modelAndView.addObject("itemList", itemList);
		modelAndView.setViewName("all-items");
		return modelAndView;
	}

	public ModelAndView findAllItemsByBuyer(Principal principal) {
		ModelAndView modelAndView = new ModelAndView();

		List<Item> itemList = itemRepo.findAll();
		modelAndView.addObject("catList", categoryService.getAllCategories());
		modelAndView.addObject("username", principal.getName());
		modelAndView.addObject("itemList", itemList);
		modelAndView.setViewName("user/items-for-user");
		return modelAndView;
	}

	public ModelAndView viewItem(int itemId) {
		return null;
	}

	public Item saveItem(Item item) {
		return itemRepo.save(item);
	}

	public Item findByItemId(int id) {
		return itemRepo.findByItemLotNumber(id);
	}
}
