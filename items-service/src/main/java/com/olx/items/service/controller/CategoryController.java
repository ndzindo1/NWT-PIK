package com.olx.items.service.controller;

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

import com.olx.items.service.businesslogic.CategoryManager;
import com.olx.items.service.models.Category;
import com.olx.items.service.validation.CategoryModelValidator;

@RestController
@RequestMapping("olx/category/")
public class CategoryController {
	
	@Autowired
	private CategoryManager categoryManager;
	@Autowired
	private CategoryModelValidator categoryModelValidator;
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestBody Category category, Errors errors) {
		
		categoryModelValidator.validate(category, errors);
		
		if (errors.hasErrors()) {
		    return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(categoryManager.save(category), HttpStatus.OK);
    }
		
	@RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Category> getAllCategory() {
		return categoryManager.getAllCategory();
    }
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Category getCategoryById(@PathVariable("id") Long id) {
		return categoryManager.getCategoryById(id);
    }
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteCategoryById(@PathVariable("id") Long id) {
		categoryManager.delete(id);
    }
}
