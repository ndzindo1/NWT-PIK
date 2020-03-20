package com.olx.user.service.businesslogic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.olx.user.service.businesslogic.UserManager;
import com.olx.user.service.models.User;
import com.olx.user.service.repositories.UserRepository;

@Component
public class DefaultUserManager implements UserManager {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void save(User user) {
		userRepository.save(user);
	}
}
