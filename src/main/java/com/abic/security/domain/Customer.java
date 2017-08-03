package com.abic.security.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 客户
 */
@Entity
@Table(name ="customers")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id; // id
	private String name; // 姓名
	private Set<Order> orders = new HashSet<Order>(); // 其下订单

	public Customer() {
	}
	
	public Customer(Integer id, String name, Set<Order> orders) {
		super();
		this.id = id;
		this.name = name;
		this.orders = orders;
	}



	@Id
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";
	}

}
