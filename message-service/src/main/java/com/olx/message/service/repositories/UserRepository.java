package com.olx.message.service.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.olx.message.service.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
}
