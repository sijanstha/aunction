package com.fortheby.aunctionitem.repo;

import com.fortheby.admin.entity.Category;
import com.fortheby.aunctionitem.entity.Item;
import com.fortheby.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("itemRepo")
public interface ItemRepository extends JpaRepository<Item, Integer>{

	List<Item> findAllByCategory(Category cat);
	
	List<Item> findAllByCategoryAndUser(Category cat, User user);
	
	List<Item> findAllByUser(User user);
	
	@Query(value="SELECT * FROM items i WHERE i.uploaded_by=?1 AND i.title LIKE ?2", nativeQuery=true)
	List<Item> searchItemByUserAndItemName(int userid, String itemName);
	
	@Query(value="SELECT * FROM items i WHERE i.title LIKE ?1", nativeQuery=true)
	List<Item> searchItemByItemName(String itemName);
	
	Item findByItemLotNumber(int id);
	
	@Transactional
	@Modifying
	@Query(value = "delete from items where item_id=?1", nativeQuery = true)
	void deleteByItemLotNumber(int itemId);
}
