package com.app.service;



import com.app.dto.LoginRequest;
import com.app.dto.FarmerLoginResponse;
import com.app.entities.Farmer;

import com.app.entities.Product;


public interface IFarmerService {
	
	Farmer registerFarmer(Farmer transientFarmer);
	
	Farmer findFarmerById(long farmerId);
	
	FarmerLoginResponse farmerLogin(LoginRequest loginRequest);
	
		void farmerLogout();
		
	Product addProduct(Product transientProduct);
	
	Product updateProduct(Product persistentProduct);
	
	String deleteProduct(Product detachedProduct);
	
}
