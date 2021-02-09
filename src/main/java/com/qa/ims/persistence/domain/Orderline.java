package com.qa.ims.persistence.domain;

public class Orderline {

	private Long orderID;
	private Long itemID;
	private long orderlineQuantity;
	
	public Orderline(Long orderID, Long itemID, Long orderlineQuantity) {
		super();
		this.orderID = orderID;
		this.itemID = itemID;
		this.orderlineQuantity = orderlineQuantity;
	}
	
	public Orderline(Long orderID, Long itemID) {
		super();
		this.orderID = orderID;
		this.itemID = itemID;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public Long getItemID() {
		return itemID;
	}

	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}

	public Long getOrderlineQuantity() {
		return orderlineQuantity;
	}

	public void setOrderlineQuantity(Long orderlineQuantity) {
		this.orderlineQuantity = orderlineQuantity;
	}

	@Override
	public String toString() {
		return "Order ID = " + orderID + " Item ID = " + itemID + " Item Quantity = " + orderlineQuantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemID == null) ? 0 : itemID.hashCode());
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		result = prime * result + (int) (orderlineQuantity ^ (orderlineQuantity >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orderline other = (Orderline) obj;
		if (itemID == null) {
			if (other.itemID != null)
				return false;
		} else if (!itemID.equals(other.itemID))
			return false;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (orderlineQuantity != other.orderlineQuantity)
			return false;
		return true;
	}
	
	
	
	
}
