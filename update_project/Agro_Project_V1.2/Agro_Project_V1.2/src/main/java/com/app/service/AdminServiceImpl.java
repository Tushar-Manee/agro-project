package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.AdminRepository;
import com.app.dto.AdminLoginResponse;
import com.app.dto.LoginRequest;
import com.app.entities.Admin;
@Service
@Transactional
public class AdminServiceImpl implements IAdminService {
	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public Admin registerAdmin(Admin transientAdmin) {
		// TODO Auto-generated method stub
		return adminRepo.save(transientAdmin);
	}

	@Override
	public Admin findAdminById(long adminId) {
		// TODO Auto-generated method stub
		return adminRepo.searchByAdminId(adminId);
	}

	@Override
	public AdminLoginResponse adminLogin(LoginRequest loginRequest) {
		Admin authenticatedAdmin= adminRepo.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
//		System.out.print(authenticatedAdmin);
		AdminLoginResponse loginResponse = mapper.map(authenticatedAdmin, AdminLoginResponse.class);
		return loginResponse;
	}

	@Override
	public void AdminLogout() {
		// TODO Auto-generated method stub
		
	}

	
}
