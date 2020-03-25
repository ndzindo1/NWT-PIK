package com.olx.transaction.service.businesslogic;

import com.olx.transaction.service.models.Product;

public interface ProductManager {
	
	Product getProductById(Long id);
	
	Boolean add(Product newProduct);
}
