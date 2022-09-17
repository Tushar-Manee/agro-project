package com.app.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id") 
	private int orderId;

	@Column(name = "total_order_price")
	private double orderTotalPrice;
	
	@Column(name = "order_item_count")
	private double orderItemCount;
	
	@Column(name="order_time")
	private LocalDateTime orderTime;

	@OneToMany(mappedBy = "order")
	Set<OrderItem>orderItems = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	private Admin admin;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	
	public Order(int orderId, double orderTotalPrice, double orderItemCount, LocalDateTime orderTime,
			Set<OrderItem> orderItems, OrderStatus orderStatus) {
		super();
		this.orderId = orderId;
		this.orderTotalPrice = orderTotalPrice;
		this.orderItemCount = orderItemCount;
		this.orderTime = orderTime;
		this.orderItems = orderItems;
		this.orderStatus = orderStatus;
	}

}
