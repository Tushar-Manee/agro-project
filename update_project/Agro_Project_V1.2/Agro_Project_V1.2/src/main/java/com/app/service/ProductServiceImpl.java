package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.ProductRepository;
import com.app.entities.Farmer;
import com.app.entities.OrderItem;
import com.app.entities.Product;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	IFarmerService farmerService;
	
	@Autowired 
	ISubCategoryService subCategoryService;

	@Override
	public Product addNewProduct(Product transientProduct,long farmerId) {
		
		Farmer farmer=farmerService.findFarmerById(farmerId);
		System.out.println(farmer);
		transientProduct.setFarmer(farmer);
		return productRepo.save(transientProduct);
	}

	@Override
	public Product updateProduct(Product detachedProduct,long farmerId) {
		
		Farmer farmer=farmerService.findFarmerById(farmerId);
		System.out.println("in farmer"+farmer);
		
		if (productRepo.existsByProductId(detachedProduct.getProductId())) {
			Product updatedProduct=productRepo.save(detachedProduct);
			updatedProduct.setFarmer(farmer);
			return productRepo.save(updatedProduct);//update
		}
		throw new ResourceNotFoundException("Invalid Product ID : Updation Failed !!!!!!!!!" + detachedProduct.getProductId());
	}
	
	@Override
	public String deleteProduct(long productId) {
		productRepo.deleteByProductId(productId);
		return "Product details deleted for product id " + productId;
	}
    
	
	//called in orderItem
	public Product findProductByProductId(int productId) {
		return productRepo.findProductByProductId(productId);
	}

	@Override
	public void updateProductQuantity(OrderItem orderItem) {
		Product product= productRepo.findProductByProductId((orderItem.getProduct().getProductId()));
         double oldQuantity=product.getAvailableStock();
         double orderedItemQuantity=orderItem.getProductQuantity();
         double updatedQuantity=oldQuantity-orderedItemQuantity;
		productRepo.updateProductQuantity(orderItem.getProduct().getProductId(),updatedQuantity);
		// also subcategories total stock has to be updated
		subCategoryService.updateSubCategoryQuantity(product.getSubcatg().getSubCatgId(),orderedItemQuantity);
	}
	
	@Override
	public void updateProductAddQuantity(OrderItem orderItem) {
		
		Product product= productRepo.findProductByProductId((orderItem.getProduct().getProductId()));
        double oldQuantity=product.getAvailableStock();
        double orderedItemQuantity=orderItem.getProductQuantity();
        //we have to add quantity to product
        double updatedQuantity=oldQuantity+orderedItemQuantity;
        productRepo.updateProductQuantity(orderItem.getProduct().getProductId(),updatedQuantity);
        subCategoryService.updateSubCategoryAddQuantity(product.getSubcatg().getSubCatgId(),orderedItemQuantity);
	}

}
