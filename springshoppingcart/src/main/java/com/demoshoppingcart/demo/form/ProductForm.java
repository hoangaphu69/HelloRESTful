package com.demoshoppingcart.demo.form;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {

	private String code;
	private String name;
	private double price;
	
	private boolean newProduct = false;
	
//	upload file
	private MultipartFile fileData;

	public ProductForm() {
		this.newProduct  = true;
	}

	public ProductForm(String code, String name, double price) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
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

	public boolean isNewProduct() {
		return newProduct;
	}

	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}

	public MultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(MultipartFile fileData) {
		this.fileData = fileData;
	} 
	
	

	
	
}
