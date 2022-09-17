package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entities.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	Optional<Admin> findByEmailAndPassword(String email, String password);
	
	@Query("select new com.app.entities.Admin(A.adminId,A.name,A.email,A.password,A.contactNumber)from Admin A where adminId=?1")
	Admin searchByAdminId(long adminId);
}
