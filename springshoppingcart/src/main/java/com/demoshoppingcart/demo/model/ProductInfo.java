package com.demoshoppingcart.demo.model;

import com.demoshoppingcart.demo.entity.Product;

public class ProductInfo {
	private String code;
	private String name;
	private double price;
	
	
	public ProductInfo(Product product) {
		
		this.code = product.getCode();
		this.name = product.getName();
		this.price = product.getPrice();
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
//	Integer integer = new Integer(3);
	
}
