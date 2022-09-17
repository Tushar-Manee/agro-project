package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.AddressRepository;
import com.app.entities.Address;

@Service
@Transactional
public class AddressServiceImpl implements IAddressService {
@Autowired
AddressRepository addressRepo;
	@Override
	public Address addAddressDetails(Address address) {
		// TODO Auto-generated method stub
		return addressRepo.save(address);
	}

	
}
