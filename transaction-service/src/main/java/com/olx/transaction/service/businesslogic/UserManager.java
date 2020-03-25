package com.olx.transaction.service.businesslogic;

import com.olx.transaction.service.models.User;

public interface UserManager {
	
	User getUserById(Long id);
	
	Boolean add(User user);
}
