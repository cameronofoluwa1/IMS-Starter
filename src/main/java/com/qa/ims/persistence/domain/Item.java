package com.qa.ims.persistence.domain;

public class Item {
	
	private Long productID;
	private String productName;
	private String productDescription;
	private Double productValue;
	private Long productStockLevels;
	
	public Item(Long product_ID, String product_name, String product_description, Double product_value, Long product_stockLevels) {
		this.setId(product_ID);
		this.setProductName(product_name);
		this.setProductDescription(product_description);
		this.setProductValue(product_value);
		this.setProductStockLevels(product_stockLevels);
	}
	
	public Item(String product_name, String product_description, Double product_value, Long product_stockLevels) {
		this.setId(productID);
		this.setProductName(product_name);
		this.setProductDescription(product_description);
		this.setProductValue(product_value);
		this.setProductStockLevels(product_stockLevels);
	}
	
	public Item(Long product_ID, String product_name, String product_description) {
		this.setId(product_ID);
		this.setProductName(product_name);
		this.setProductDescription(product_description);
	}
	
	public Item(String product_name, String product_description) {
		this.setProductName(product_name);
		this.setProductDescription(product_description);
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
