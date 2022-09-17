package com.app.service;

import com.app.dto.AdminLoginResponse;
import com.app.dto.LoginRequest;
import com.app.entities.Admin;

public interface IAdminService {
	
	Admin registerAdmin(Admin transientAdmin);
	
	Admin findAdminById(long adminId);
	
	AdminLoginResponse adminLogin(LoginRequest loginRequest);
	
	void AdminLogout();
}
