package com.olx.user.service.repositories;

import org.springframework.data.repository.CrudRepository;

import com.olx.user.service.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
