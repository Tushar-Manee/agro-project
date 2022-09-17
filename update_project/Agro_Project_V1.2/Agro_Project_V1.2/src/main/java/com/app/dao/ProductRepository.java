package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entities.Product;
  @Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	String deleteByProductId(long productId);
	
	boolean existsByProductId(long productId );

	//long productId, String productTitle, double price, LocalDate expiryDate, int availableStock
	@Query("select new com.app.entities.Product(p.productId,p.productTitle,p.price,p.expiryDate,p.availableStock) from Product p where p.productId= ?1")
	Product findProductByProductId(long productId);

	@Modifying
	@Query("update Product p set p.availableStock=?2 where p.productId=?1")
	void updateProductQuantity(long productId,double Quantity);
}
