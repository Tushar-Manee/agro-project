package com.app.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.CustomerRepository;
import com.app.dto.CustomerLoginrResponse;
import com.app.dto.LoginRequest;
import com.app.entities.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {
@Autowired
CustomerRepository customerRepo;
@Autowired
private ModelMapper mapper;

	@Override
	public Customer registerCustomer(Customer transientCustomer) {
		// TODO Auto-generated method stub
		return customerRepo.save(transientCustomer);
	}

	@Override
	public void customerLogout() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public CustomerLoginrResponse customerLogin(@Valid LoginRequest request) {
		Customer authenticatedCustomer= customerRepo.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		CustomerLoginrResponse loginResponse = mapper.map(authenticatedCustomer, CustomerLoginrResponse.class);
		return loginResponse;
	}

	@Override
	public Customer findCustomerById(long customerId) {
		// TODO Auto-generated method stub
		Customer authenticatedCustomer = customerRepo.findByCustomerId(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!! of customer"));
		return authenticatedCustomer;
	}

}
