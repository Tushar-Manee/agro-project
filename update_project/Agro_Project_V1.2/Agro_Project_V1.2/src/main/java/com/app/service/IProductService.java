package com.app.service;

import com.app.entities.OrderItem;
import com.app.entities.Product;

public interface IProductService {
	
	Product addNewProduct(Product tansientProduct,long farmerId);
	
	Product updateProduct(Product detachedProduct,long farmerId);
	
	String deleteProduct(long productId);

	Product findProductByProductId(int productId);

	void updateProductQuantity(OrderItem orderItem);
	
	void updateProductAddQuantity(OrderItem orderItem);
	
	
}
