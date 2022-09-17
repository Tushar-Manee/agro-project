package com.app.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "address")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_Id") 
	private int addressId;
	
	@Column(length = 50, name = "adr_line1")
	private String adrLine1;
	@Column(length = 50, name = "adr_line2")
	private String adrLine2;
	@Column(length = 20)
	private String city;
	@Column(length=20)
	private String taluka;
	@Column(length = 20)
	private String district;
	@Column(length = 20)
	private String state;
	@Column(length = 20)
	private String country;
	
	@Column(length = 6)
	//@Pattern(regexp = "^[1-9]{1}[0-9]{2}\\s{0, 1}[0-9]{3}$")
	//@NotBlank(message = "pincode is must")
	private String pinCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "farmer_id")
    private Farmer farmer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	
}
