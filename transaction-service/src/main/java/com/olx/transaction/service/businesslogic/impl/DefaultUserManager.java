package com.olx.transaction.service.businesslogic.impl;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.olx.transaction.service.businesslogic.UserManager;
import com.olx.transaction.service.models.User;
import com.olx.transaction.service.repositories.UserRepository;

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
		return userRepository.save(newUser) != null;
	}
}
