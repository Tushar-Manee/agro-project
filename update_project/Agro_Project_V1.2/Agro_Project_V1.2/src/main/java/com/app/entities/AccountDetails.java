package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "account_details")
@NoArgsConstructor
@Getter
@Setter 
public class AccountDetails {
 
	@Id
	@Column(name = "bank_account_number")
	private String bankAccountNumber;
	
	@Column(name = "upi_id")
	private String upiId;
	
	@Column(length = 20)
	@NotBlank(message = "Phone number is must")
	private String contactNumber;
	
	@Column(name="bank_name")
	private String BankName;
	
	@Column(name="branch")
	private String Branch;
	
	@Column(name = "ifsc_code")
	private int ifsc;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "farmer_id")
	private Farmer farmer;
}
