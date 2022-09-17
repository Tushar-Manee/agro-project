package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entities.Farmer;
@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {
	Optional<Farmer> findByEmailAndPassword(String email, String password);

//	Farmer findByFarmerId(long farmerId);

	@Query("select new com.app.entities.Farmer(f.farmerId,f.lastName,f.firstName,f.email,f.password,f.contactNumber,f.profilePicture,f.maintainanceAmount,f.receivableAmount) from Farmer f where farmerId=?1")
	Farmer searchByFarmerId(long farmerId);

}
