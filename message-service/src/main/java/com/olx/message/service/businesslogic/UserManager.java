package com.olx.message.service.businesslogic;

import com.olx.message.service.models.User;

public interface UserManager {
	
	User getUserById(Long id);
	
	Boolean add(User user);
}
