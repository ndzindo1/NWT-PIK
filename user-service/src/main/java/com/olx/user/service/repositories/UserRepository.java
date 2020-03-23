package com.olx.user.service.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.olx.user.service.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findById(Long id);
	
	User findByEmail(String email);

	@Transactional 
	void deleteById(Long id);
}
