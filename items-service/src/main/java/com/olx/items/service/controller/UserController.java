package com.olx.items.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olx.items.service.businesslogic.UserManager;
import com.olx.items.service.models.User;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("olx/items/users/")
@Api(tags = { "User Controller" })
public class UserController {
	
	@Autowired
	private UserManager userManager;

	@RequestMapping(value = "add", method = RequestMethod.PUT)
    public Boolean addUser(@RequestBody User user) {
		return userManager.add(user);
    }

}
