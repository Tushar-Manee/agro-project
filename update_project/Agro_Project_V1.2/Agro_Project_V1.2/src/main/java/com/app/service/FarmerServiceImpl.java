package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.FarmerRepository;
import com.app.dto.FarmerLoginResponse;
import com.app.dto.LoginRequest;
import com.app.entities.Farmer;
import com.app.entities.Product;


@Service
@Transactional
public class FarmerServiceImpl implements IFarmerService {
	@Autowired
	FarmerRepository farmerRepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public Farmer registerFarmer(Farmer transientFarmer) {
		// TODO Auto-generated method stub
		return farmerRepo.save(transientFarmer);
	}

	@Override
	public FarmerLoginResponse farmerLogin(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		Farmer authenticatedFarmer= farmerRepo.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
//		System.out.print(authenticatedFarmer);
		FarmerLoginResponse loginResponse = mapper.map(authenticatedFarmer, FarmerLoginResponse.class);
		return loginResponse;
	}

	@Override
	public Farmer findFarmerById(long farmerId) {
		System.out.print("in find method");
		return farmerRepo.searchByFarmerId(farmerId);
	}

	@Override
	public void farmerLogout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product addProduct(Product transientProduct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product updateProduct(Product persistentProduct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteProduct(Product detachedProduct) {
		// TODO Auto-generated method stub
		return null;
	}
}
