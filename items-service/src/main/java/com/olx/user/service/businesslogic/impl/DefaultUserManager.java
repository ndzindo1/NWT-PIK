package com.olx.user.service.businesslogic.impl;

import java.net.URI;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.social.support.URIBuilder;

import com.olx.items.service.businesslogic.UserManager;
import com.olx.items.service.models.User;

@Component
public class DefaultUserManager implements UserManager{
	
	private String baseUrl = "http://localhost:8080/olx/users/";
	
	private RestTemplate restTemplate;

	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
	}
	
	@Override
	public User getUserById(String id) {
		URI uri = URIBuilder.fromUri(baseUrl + id).build();
		return restTemplate.getForObject(uri, User.class);
	}
}
