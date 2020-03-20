package com.olx.user.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olx.user.service.businesslogic.UserManager;
import com.olx.user.service.models.User;

@RestController
public class UserController {
	
	@Autowired
	private UserManager userManager;
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(@RequestBody User user) {
		userManager.save(user);
		return "Novi korisnik sacuvan!";
    }
}
