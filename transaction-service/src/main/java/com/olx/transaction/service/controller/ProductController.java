package com.olx.transaction.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olx.transaction.service.businesslogic.ProductManager;
import com.olx.transaction.service.models.Product;

@RestController
@RequestMapping("olx/transaction/products/")
public class ProductController {
	
	@Autowired
	private ProductManager productManager;

	@RequestMapping(value = "add", method = RequestMethod.PUT)
    public Boolean addUser(@RequestBody Product product) {
		return productManager.add(product);
    }

}
