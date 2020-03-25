package com.olx.items.service.businesslogic.impl;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.olx.items.service.businesslogic.UserManager;
import com.olx.items.service.models.User;
import com.olx.items.service.repositories.UserRepository;

@Component
public class DefaultUserManager implements UserManager{
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public Boolean add(User newUser) {
		User user = userRepository.save(newUser);
		return user != null;
	}
}
