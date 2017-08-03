package com.abic.security.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 订单
 */
@Entity
@Table(name ="orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id; // id
	private String orderNumber; // 订单号
	private Customer customer; // 所属客户

	public Order() {
	}
	
	public Order(Integer id, String orderNumber, Customer customer) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.customer = customer;
	}

	@Id	
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ordernumber")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNumber=" + orderNumber + "]";
	}

}
