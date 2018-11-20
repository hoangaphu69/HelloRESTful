package com.demoshoppingcart.demo.model;

import com.demoshoppingcart.demo.form.CustomerForm;

public class CustomerInfo {
	private String name;	
	private String address;
	private String mail;
	private String phone;
	
	private boolean valid;

	
	
	public CustomerInfo(CustomerForm customerForm) {
//		super();
		this.name = customerForm.getName();
		this.address = customerForm.getAddress();
		this.mail = customerForm.getEmail();
		this.phone = customerForm.getPhone();
		this.valid = customerForm.isValid();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
}
