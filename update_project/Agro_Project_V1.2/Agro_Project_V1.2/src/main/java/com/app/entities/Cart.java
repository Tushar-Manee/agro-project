package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart")
@NoArgsConstructor
@Getter
@Setter

public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private Long cartId;

	@OneToOne(/*cascade = CascadeType.ALL*/ fetch = FetchType.LAZY)
	@JoinColumn(name = "cust_id", nullable = false)
	private Customer customer;

	@Column(name = "cart_total_price")
	private double cartTotalPrice;

	@OneToMany(mappedBy = "cart")
	List<Order> orders = new ArrayList<>();

	@OneToMany(mappedBy = "cart")
	List<OrderItem> orderItems = new ArrayList<>();

	private int orderItemsQuantity;
	
	public Cart(Customer customer,double cartTotalPrice,int orderItemsQuantity) {
		this.customer = customer;
		
		this.cartTotalPrice = cartTotalPrice;
		this.orderItemsQuantity = orderItemsQuantity;
	}

	public Cart(Long cartId, double cartTotalPrice, int orderItemsQuantity) {
		super();
		this.cartId = cartId;
		this.cartTotalPrice = cartTotalPrice;
		this.orderItemsQuantity = orderItemsQuantity;
	}

	public Cart(Long cartId, double cartTotalPrice, int orderItemsQuantity, Customer customer) {
		super();
		this.cartId = cartId;
		this.customer = customer;
		this.cartTotalPrice = cartTotalPrice;
		this.orderItemsQuantity = orderItemsQuantity;
	}

}
