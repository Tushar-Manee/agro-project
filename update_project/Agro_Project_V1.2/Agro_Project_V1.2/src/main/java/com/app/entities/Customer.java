package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@Getter
@Setter
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long customerId;

	@NotBlank(message = "first name is required")
	@Column(length = 20, name = "first_name")
	private String firstName;

	@NotBlank(message = "last name is required")
	@Column(length = 20, name = "last_name")
	private String lastName;

	@Column(length = 50)
	@NotBlank(message = "email is required")
	@Email(message = "invalid email format")
	private String email;

	@Column(length = 20)
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Invalid password")
	private String password;

	@Column(name = "contact_number")
	@Length(max = 10)
	@NotBlank(message = "Phone number is must")
	private String contactNumber;

	@Lob
	@Column(name = "profile_picture")
	private byte[] profilePicture;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Address> addresses=new ArrayList<>();

	@OneToOne(mappedBy = "customer",fetch = FetchType.LAZY)
	private Cart cart;
	
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "customer")
	List<AccountDetails> accountDetails=new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	private Admin admin;

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + ", contactNumber=" + contactNumber + "]";
	}

	public Customer(Long customerId,  String firstName,
			String lastName,
			 String email,
			 String password,
			 String contactNumber, byte[] profilePicture) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.contactNumber = contactNumber;
		this.profilePicture = profilePicture;
		//this.cart = cart;
	}
}
