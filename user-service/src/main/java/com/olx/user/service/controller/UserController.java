package com.olx.user.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olx.user.service.businesslogic.UserManager;
import com.olx.user.service.models.Login;
import com.olx.user.service.models.User;
import com.olx.user.service.validation.LoginModelValidator;
import com.olx.user.service.validation.UserModelValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("olx/users/")
@Api(tags = { "User Controller" })
public class UserController {
	
	@Autowired
	private UserManager userManager;
	@Autowired
    private UserModelValidator userModelValidator;
	@Autowired
	private LoginModelValidator loginModelValidator;
	
	@ApiOperation(value = "Register a new user", notes = "This service method is used to register a new user to the system.")
	@RequestMapping(value = "post", method = RequestMethod.POST)
	public ResponseEntity<Object> register(@RequestBody User user, Errors errors) {

		userModelValidator.validate(user, errors);
		
		if (!errors.hasErrors()) {
		   	return new ResponseEntity<Object>(userManager.save(user), HttpStatus.OK);
		}

		return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
	}
	
	@ApiOperation(value = "Update a user", notes = "This service method is used to update user information.")
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @RequestBody User user, Errors errors) {

		userModelValidator.validateUpdate(user, errors);
		
		if (errors.hasErrors()) {
		    return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		
		User updatedUser = userManager.update(user, id);

		return new ResponseEntity<Object>(updatedUser, HttpStatus.OK);
    }
	
	@ApiOperation(value = "Login", notes = "This service method is used to retrieve information about user credentials.")
	@RequestMapping(value = "login", method = RequestMethod.POST)
    public  ResponseEntity<Object> login(@RequestBody Login login, Errors errors) {
		
		loginModelValidator.validate(login, errors);
		
		if (errors.hasErrors()) {
		    return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(userManager.getUserByEmail(login.getEmail()), HttpStatus.OK);
    }
	
	@ApiOperation(value = "All users", notes = "This service method is used to retrieve all users in system.")
	@RequestMapping(value = "all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
		return userManager.getAllUsers();
    }
	
	@ApiOperation(value = "Get user by id",	notes = "This service method is used to retrieve a user with a specific id.")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") Long id) {
		return userManager.getUserById(id);
    }
	
	@ApiOperation(value = "Delete user", notes = "This service method is used to delete a user with a specific id.")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable("id") Long id) {
		userManager.delete(id);
    }
}
