package com.fortheby.admin.repo;

import com.fortheby.admin.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("categoryRepo")
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Category findByCategoryId(int catId);
	
	@Query("SELECT c FROM Category c WHERE c.categoryName=?1")
	Category findByCategoryName(String categoryName);
	
	List<Category> findAll();

	@Transactional
	void deleteByCategoryId(int catId);
}
