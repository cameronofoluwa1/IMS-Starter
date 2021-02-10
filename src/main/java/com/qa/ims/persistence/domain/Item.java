package com.qa.ims.persistence.domain;

public class Item {
	
	private Long productID;
	private String productName;
	private String productDescription;
	private Double productValue;
	private Long productStockLevels;
	
	public Item(Long productID, String productName, String productDescription, Double productValue, Long productStockLevels) {
		this.setId(productID);
		this.setProductName(productName);
		this.setProductDescription(productDescription);
		this.setProductValue(productValue);
		this.setProductStockLevels(productStockLevels);
	}
	
	public Item(String productName, String productDescription, Double productValue, Long productStockLevels) {
		this.setId(productID);
		this.setProductName(productName);
		this.setProductDescription(productDescription);
		this.setProductValue(productValue);
		this.setProductStockLevels(productStockLevels);
	}
	
	public Item(Long productID, String productName, String productDescription) {
		this.setId(productID);
		this.setProductName(productName);
		this.setProductDescription(productDescription);
	}
	
	public Item(String productName, String productDescription) {
		this.setProductName(productName);
		this.setProductDescription(productDescription);
	}

	public Long getproductID() {
		return productID;
	}

	public void setId(Long productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Double getProductValue() {
		return productValue;
	}

	public void setProductValue(Double productValue) {
		this.productValue = productValue;
	}

	public Long getProductStockLevels() {
		return productStockLevels;
	}

	public void setProductStockLevels(Long productStockLevels) {
		this.productStockLevels = productStockLevels;
	}

	@Override
	public String toString() {
		return "ID = " + productID + " Product name = " + productName + " Product Description = " + productDescription+ " Product Value = £" + productValue + " Product Stock Levels = " + productStockLevels;
	}
}
