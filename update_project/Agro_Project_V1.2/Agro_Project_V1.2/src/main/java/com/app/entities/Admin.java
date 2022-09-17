package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admin")
@NoArgsConstructor
@Getter
@Setter
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	private long adminId;

	@Column(name = "name")
	@NotBlank(message = "name is required")
	private String name;

	@Column(length = 50)
	@NotBlank(message = "email is required")
	@Email(message = "invalid email format")
	private String email;

	@Column(length = 20)
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Invalid password")
	private String password;

	@Column(name = "contact_number")
	@NotBlank(message = "Phone number is must")
	@Length(max = 10)
	private String contactNumber;

	@OneToMany(mappedBy = "admin", fetch = FetchType.EAGER)
	List<Farmer> farmers = new ArrayList<>();

	@OneToMany(mappedBy = "admin")
	List<Customer> customers = new ArrayList<>();

	@OneToMany(mappedBy = "admin")
	List<Order> orders = new ArrayList<>();
	
	@OneToMany(mappedBy = "admin")
	List<Transaction> transactions = new ArrayList<>();

	@OneToMany(mappedBy = "admin")
	List<Category> categories = new ArrayList<>();

	@OneToMany(mappedBy = "admin")
	List<SubCategory> subCategories = new ArrayList<>();

	@OneToMany(mappedBy = "admin")
	List<Product> products = new ArrayList<>();

	public Admin(long adminId, String name, String email, String password, String contactNumber) {
		
		super();
		System.out.println("1");
		this.adminId = adminId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.contactNumber = contactNumber;
	}

}
