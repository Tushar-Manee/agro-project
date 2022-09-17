package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entities.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findByEmailAndPassword(String email, String password);

	/*
	 * Long customerId, String firstName, String lastName, String email, String
	 * password, String contactNumber, byte[] profilePicture
	 */
	@Query("select new com.app.entities.Customer(c.customerId,c.firstName,c.lastName,c.email,c.password,c.contactNumber,c.profilePicture) from Customer c where customerId=?1")
	Optional<Customer> findByCustomerId(Long customerId);
}
