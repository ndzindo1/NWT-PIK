package com.olx.items.service.businesslogic;

import com.olx.items.service.models.User;

public interface UserManager {
	
	User getUserById(Long id);
	
	Boolean add(User user);
}
