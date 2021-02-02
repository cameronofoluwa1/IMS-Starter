package com.qa.ims.persistence.domain;

public class Orderline {

	private Long order_ID;
	private Long item_ID;
	private long orderline_quantity;
	
	public Orderline(Long order_ID, Long item_ID, Long orderline_quantity) {
		super();
		this.order_ID = order_ID;
		this.item_ID = item_ID;
		this.orderline_quantity = orderline_quantity;
	}

	public Long getOrder_ID() {
		return order_ID;
	}

	public void setOrder_ID(Long order_ID) {
		this.order_ID = order_ID;
	}

	public Long getItem_ID() {
		return item_ID;
	}

	public void setItem_ID(Long item_ID) {
		this.item_ID = item_ID;
	}

	public Long getorderline_quantity() {
		return orderline_quantity;
	}

	public void setorderline_quantity(Long orderline_quantity) {
		this.orderline_quantity = orderline_quantity;
	}

	@Override
	public String toString() {
		return "Order ID = " + order_ID + " Item ID = " + item_ID + " Item Quantity = " + orderline_quantity;
	}
	
	
}
