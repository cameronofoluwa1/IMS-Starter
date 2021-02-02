package com.qa.ims.persistence.domain;

import java.util.HashSet;
import java.util.Set;

public class Orders {
	
	private Long order_ID;
	private Long customer_id;
	private Set<ItemRef> items = new HashSet<>();

	public Orders(Long order_ID, Long customer_id) {
		super();
		this.order_ID = order_ID;
		this.customer_id = customer_id;
	}
	
	public Orders(Long customer_id) {
		super();
		this.customer_id = customer_id;
	}
	
	void addItem(Item item) {
		this.items.add(new ItemRef(item.getproduct_ID()));
	}
	
	public Long getOrder_ID() {
		return order_ID;
	}

	public void setOrder_ID(Long order_ID) {
		this.order_ID = order_ID;
	}

	
	public Long getcustomer_id() {
		return customer_id;
	}


	public void setcustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}


	@Override
	public String toString() {
		return "Order ID = " + order_ID + " Customer ID " + customer_id;
	}
}
