package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AdminLoginResponse;
import com.app.dto.ApiResponse;
import com.app.dto.LoginRequest;
import com.app.entities.Admin;
import com.app.service.IAdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

	@Autowired
	private IAdminService adminService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerAdminDetails(@RequestBody @Valid Admin transientAdmin,HttpSession session) {
		System.out.println("in add dtls " + transientAdmin);
		try {
			Admin registeredAdmin=adminService.registerAdmin(transientAdmin);
			session.setAttribute("admin_dtls",registeredAdmin );
			// invoke service layer method
			return new ResponseEntity<>(registeredAdmin, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in add farmer " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);// => invalid data from
																									// clnt
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest request,HttpSession session)	
	{
		System.out.println("in user login "+request);
		AdminLoginResponse loggedAdmin =adminService.adminLogin(request);
		session.setAttribute("admin_dtls", loggedAdmin.getAdminId());
		return ResponseEntity.ok(adminService.adminLogin(request));
	}
	
	@GetMapping("/logout")
	public String adminLogout(HttpSession session,Model map,HttpServletResponse resp,HttpServletRequest request)
	{
		System.out.println("in log out");
		map.addAttribute("admin_dtls", session.getAttribute("admin_dtls"));
		session.invalidate();
		resp.setHeader("refresh", "5;url="+request.getContextPath());
		return "logged out!!!!";
	}	
}
