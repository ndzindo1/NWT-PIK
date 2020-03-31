package com.olx.user.service.businesslogic.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.olx.user.service.businesslogic.UserManager;
import com.olx.user.service.models.User;
import com.olx.user.service.repositories.UserRepository;

@Component
public class DefaultUserManager implements UserManager {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
	}
	
	@Override
	public User save(User user) {
		User isAdded = userRepository.save(user);
		if (isAdded != null) {
			if(!(addUserInAnotherMicroService(user, "http://items-service/olx/items/users/add")))
				System.out.println("User nije dodan u items microservice!");
			if(!(addUserInAnotherMicroService(user, "http://message-service/olx/messages/users/add")))
				System.out.println("User nije dodan u message microservice!");
			if(!(addUserInAnotherMicroService(user, "http://transaction-service/olx/transaction/users/add")))
				System.out.println("User nije dodan u transaction microservice!");
		}
		return null;
	}


	@Override
	public List<User> getAllUsers() {
		List<User> result = new ArrayList<User>();
		for(User user: userRepository.findAll())
			result.add(user);
		return result;
	}


	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}


	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}


	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}


	@Override
	public User update(User update, Long id) {
		
		User current = getUserById(id);
		
		if(update!=null && current != null) {
			if(update.getPassword() != null) 
				current.setPassword(update.getPassword());
			if(update.getEmail() != null) 
				current.setEmail(update.getEmail());
			if(update.getFirstName() != null) 
				current.setFirstName(update.getFirstName());
			if(update.getLastName() != null) 
				current.setLastName(update.getLastName());
			if(update.getPhoneNumber() != null) 
				current.setPhoneNumber(update.getPhoneNumber());
			if(update.getLocation() != null) 
				current.setLocation(update.getLocation());
		}
		
		return save(current);
	}
	
	public Boolean addUserInAnotherMicroService(User user, String url) {
		URI uri = URIBuilder.fromUri(url).build();
    	RequestEntity<User> request = RequestEntity.method(HttpMethod.PUT, uri)
    											.contentType(MediaType.APPLICATION_JSON)
    											.body(user);
    	
		return restTemplate.exchange(request, Boolean.class).getBody();
	}
}
