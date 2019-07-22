package com.fortheby.aunctionitem.repo;

import com.fortheby.aunctionitem.entity.BidItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bidItemRepo")
public interface BidItemRepo extends JpaRepository<BidItem, Integer> {

	BidItem findByItemIdAndUserId(int itemId, int userId);
	
	@Query(value="SELECT SUM(bid_amount) FROM bid_item_info", nativeQuery=true)
	Object[] findTotalAunctionRevenue();
	
	List<BidItem> findByUserId(int id);
	
}
