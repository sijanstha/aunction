package com.fortheby.login;

import com.fortheby.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepo")
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
	User findByUserId(int userId);
	
	@Query(value="SELECT role,COUNT(*) AS user_count FROM users GROUP BY role ASC", nativeQuery=true)
	List<Object[]> findUserCountBasedOnRole();
}
