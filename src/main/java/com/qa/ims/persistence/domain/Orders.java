package com.qa.ims.persistence.domain;

public class Orders {
	
	private Long orderID;
	private Long customerID;
	private Long productID;
	private double orderValue;

	public Orders(Long orderID, Long customerID, Long productID) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.productID = productID;
	}

	public Orders(Long orderID, Long customerID, Long productID, double orderValue) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.productID = productID;
		this.orderValue = orderValue;
	}
	
	public Orders(Long customerID) {
		super();
		this.customerID = customerID;
	}
	
	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}
	
	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	
	public Long getCustomerID() {
		return customerID;
	}


	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}


	public String toString1() {
		return "Order ID = " + orderID + " Customer ID = " + customerID + " Product ID = " + productID;
	}


	@Override
	public String toString() {
		return "Order ID = " + orderID + " Customer ID = " + customerID + " Product ID = " + productID + " order Value = £" + orderValue;
	}
}
