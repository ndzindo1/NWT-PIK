package com.olx.transaction.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olx.transaction.service.businesslogic.UserManager;
import com.olx.transaction.service.models.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("users/")
@Api(tags = { "User Controller" })
public class UserController {
	
	@Autowired
	private UserManager userManager;

	@ApiOperation(value = "Add a new user", notes = "This service method is used to add a new user to the transaction database.")
	@RequestMapping(value = "add", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
		userManager.add(user);
    }
	
	@ApiOperation(value = "Delete a user", notes = "This service method is used to delete a user from the transaction database.")
	@RequestMapping(value = "{email}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("email") String email) {
		userManager.delete(email);
    }

}
