package com.fortheby.admin.serviceimpl;

import com.fortheby.admin.entity.Category;
import com.fortheby.admin.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class CategoryServiceImpl {

	@Autowired
	private CategoryRepository categoryRepo;

	public List<Category> getAllCategories() {
		return categoryRepo.findAll();
	}

	public Category findById(int catId) {
		return categoryRepo.findByCategoryId(catId);
	}

	public ModelAndView addCategory(Category newCategory) {
		ModelAndView modelAndView = new ModelAndView();
		Category existingCat = categoryRepo.findByCategoryName(newCategory.getCategoryName());
		modelAndView.addObject("catList", getAllCategories());
		if (existingCat != null) {
			modelAndView.addObject("error", true);
			modelAndView.addObject("errorMessage", "already taken");
			modelAndView.setViewName("admin/add-category");
		} else {
			modelAndView.setViewName("admin/index");
			categoryRepo.save(newCategory);
		}

		return modelAndView;
	}

}
