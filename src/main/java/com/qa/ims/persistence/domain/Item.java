package com.qa.ims.persistence.domain;

public class Item {
	
	private Long product_ID;
	private String product_name;
	private String product_description;
	private Double product_value;
	private Long product_stockLevels;
	
	public Item(Long product_ID, String product_name, String product_description, Double product_value, Long product_stockLevels) {
		this.setId(product_ID);
		this.setProduct_name(product_name);
		this.setProduct_description(product_description);
		this.setProduct_value(product_value);
		this.setProduct_stockLevels(product_stockLevels);
	}
	
	public Item(String product_name, String product_description, Double product_value, Long product_stockLevels) {
		this.setId(product_ID);
		this.setProduct_name(product_name);
		this.setProduct_description(product_description);
		this.setProduct_value(product_value);
		this.setProduct_stockLevels(product_stockLevels);
	}
	
	public Item(Long product_ID, String product_name, String product_description) {
		this.setId(product_ID);
		this.setProduct_name(product_name);
		this.setProduct_description(product_description);
	}
	
	public Item(String product_name, String product_description) {
		this.setProduct_name(product_name);
		this.setProduct_description(product_description);
	}

	public Long getproduct_ID() {
		return product_ID;
	}

	public void setId(Long product_ID) {
		this.product_ID = product_ID;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public Double getProduct_value() {
		return product_value;
	}

	public void setProduct_value(Double product_value) {
		this.product_value = product_value;
	}

	public Long getProduct_stockLevels() {
		return product_stockLevels;
	}

	public void setProduct_stockLevels(Long product_stockLevels) {
		this.product_stockLevels = product_stockLevels;
	}

	@Override
	public String toString() {
		return "ID = " + product_ID + " Product name = " + product_name + " Product Description = " + product_description+ " Product Value = £" + product_value + " Product Stock Levels = " + product_stockLevels;
	}
	
	
}
