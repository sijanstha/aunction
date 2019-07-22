package com.fortheby.admin.controller;

import com.fortheby.admin.entity.Category;
import com.fortheby.admin.serviceimpl.CategoryServiceImpl;
import com.fortheby.aunctionitem.serviceimpl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryServiceImpl categoryService;
	@Autowired
	private ItemServiceImpl itemService;

	@GetMapping("/add")
	public ModelAndView showAddCategoryPage(ModelAndView modelAndView) {
		modelAndView.addObject("category", new Category());
		modelAndView.addObject("catList", categoryService.getAllCategories());
		modelAndView.setViewName("admin/add-category");
		return modelAndView;
	}
	
	@PostMapping("/add/save")
	public ModelAndView processNewCategory(ModelAndView modelAndView,
			@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
	       modelAndView.addObject("category", category);
	       modelAndView.addObject("catList", categoryService.getAllCategories());
	       modelAndView.setViewName("admin/add-category");
	       return modelAndView;
	    }
		return categoryService.addCategory(category);

	}
	
	@GetMapping("/item/{catId}")
	public ModelAndView showCategoryBasedItems(@PathVariable("catId") int categoryId, Principal principal) {
		return itemService.findAllItemsByCategoryId(categoryId,principal);
	}
	
	@GetMapping("delete/{catId}")
	public ModelAndView delete(@PathVariable("catId") int catId) {
		return categoryService.deleteCategory(catId);
	}
	
	
}
