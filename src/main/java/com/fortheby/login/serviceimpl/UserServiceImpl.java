package com.fortheby.login.serviceimpl;

import com.fortheby.login.UserRepository;
import com.fortheby.login.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepo;
	
	public User getCurrentUser(Principal principal) {
		String userName = principal.getName();
		return userRepo.findByUsername(userName);
	}
	
	public User getUserByUserId(int userId) {
		return userRepo.findByUserId(userId);
	}
	
	public Map<String, String> findUserCount(){
		List<Object[]> userCounts = userRepo.findUserCountBasedOnRole();
		Map<String, String> listOfUserCounts = new HashMap<>();
		for(Object[] userCount : userCounts) {
			listOfUserCounts.put(userCount[0].toString(), userCount[1].toString());
		}
		
		return listOfUserCounts;
	}
	
}
