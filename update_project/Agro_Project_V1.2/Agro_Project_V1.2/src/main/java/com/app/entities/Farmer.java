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
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "farmer")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Farmer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "farmer_Id")
	private long farmerId;

	@NotBlank(message = "first name is required")
	@Column(name = "first_name")
	private String firstName;

	@NotBlank(message = "last name is required")
	@Column(name = "last_name")
	private String lastName;

	@Column(length = 50)
	@NotBlank(message = "email is required")
	@Email(message = "invalid email format")
	private String email;

	@Column(length = 20)
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Invalid password")
	private String password;

	@Column(name = "contact_number")
	@NotBlank(message = "Phone number is must")
	private String contactNumber;

	@Lob
	@Column(name = "profile_picture")
	private byte[] profilePicture; // day12 save image and restore image

	// one farmer may have multiple products
	@OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL)
	List<Product> products=new ArrayList<>();

	@OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL)
	private List<Address> addresses=new ArrayList<>();
	
	@OneToMany(mappedBy = "farmer",cascade = CascadeType.ALL)
	private List<AccountDetails> accountDetails=new ArrayList<>();

	@Column(name = "admin_maintainance_amount")
	private double maintainanceAmount;

	@Column(name = "net_receivable_amount")
	private double receivableAmount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	private Admin admin;

	public Farmer(long farmerId, String firstName, String lastName, String email, String password, String contactNumber,
			byte[] profilePicture, double maintainanceAmount, double receivableAmount) {
		
		super();
		this.farmerId = farmerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.contactNumber = contactNumber;
		this.profilePicture = profilePicture;
		this.maintainanceAmount = maintainanceAmount;
		this.receivableAmount = receivableAmount;
	}

}
