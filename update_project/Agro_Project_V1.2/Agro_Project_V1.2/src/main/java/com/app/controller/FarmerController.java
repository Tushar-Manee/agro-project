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

import com.app.dto.ApiResponse;
import com.app.dto.FarmerLoginResponse;
import com.app.dto.LoginRequest;
import com.app.entities.Address;
import com.app.entities.Farmer;
import com.app.service.IAddressService;
import com.app.service.IFarmerService;

@RestController
@RequestMapping("/farmer")
@CrossOrigin(origins = "http://localhost:3000")
public class FarmerController {
	@Autowired
	private IFarmerService farmerService;
	@Autowired(required=true)
	private IAddressService addressService;

	@PostMapping("/register")
	public ResponseEntity<?> registerFarmerDetails(@RequestBody @Valid Farmer transientFarmer,HttpSession session) {
		System.out.println("in add dtls " + transientFarmer);
		try {
			Farmer registeredFarmer=farmerService.registerFarmer(transientFarmer);
			session.setAttribute("farmer_dtls",registeredFarmer );
			// invoke service layer method
			return new ResponseEntity<>(registeredFarmer, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in add farmer " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);// => invalid data from
																									// clnt
		}
	}
	@PostMapping("/address")
	public ResponseEntity<?> addAddress(@RequestBody @Valid Address transientAddress,HttpSession session) {
		System.out.println("in add address dtls " + transientAddress);
		try {
			Farmer registeredFarmer=(Farmer) session.getAttribute("farmer_dtls");
			transientAddress.setFarmer(registeredFarmer);
			// invoke service layer method
			return new ResponseEntity<>(addressService.addAddressDetails(transientAddress), HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in add address " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest request,HttpSession session)	
	{
		System.out.println("in user login "+request);
		FarmerLoginResponse loggedFarmer =farmerService.farmerLogin(request);
		
//		Farmer farmer=farmerService.findFarmerById(loggedFarmer.getFarmerId());
//		System.out.println(farmer);
		
		session.setAttribute("farmer_dtls", loggedFarmer.getFarmerId());
		return ResponseEntity.ok(farmerService.farmerLogin(request));
	}
	
	@GetMapping("/logout")
	public String farmerLogout(HttpSession session,Model map,HttpServletResponse resp,HttpServletRequest request)
	{
		System.out.println("in log out");
		map.addAttribute("farmer_dtls", session.getAttribute("farmer_dtls"));
		session.invalidate();
		resp.setHeader("refresh", "5;url="+request.getContextPath());
		return "logged out!!!!";
	}	
}
