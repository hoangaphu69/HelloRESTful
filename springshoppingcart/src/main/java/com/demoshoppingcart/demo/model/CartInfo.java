package com.demoshoppingcart.demo.model;

import java.util.ArrayList;
import java.util.List;

public class CartInfo {
	
	private int orderNum;
	private CustomerInfo customerInfo;
	
	private final List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();
	
	
	
	public CartInfo() {
//		super();
		// TODO Auto-generated constructor stub
	}



	public int getOrderNum() {
		return orderNum;
	}



	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}



	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}



	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}



	public List<CartLineInfo> getCartLines() {
		return cartLines;
	}
	
	
	/**
	 * tim trong gio hang
	 * */
	public CartLineInfo findLineByCode(String code) {
		
		for (CartLineInfo cartLineInfo : this.cartLines) {
			if(cartLineInfo.getProductInfo().getCode().equals(code)) {
				return cartLineInfo;
			}
		}
		return null;
	}
	
	public void addProduct(ProductInfo productInfo,int quantity) {
		CartLineInfo lineInfo = this.findLineByCode(productInfo.getCode());
		if(lineInfo == null) {
			lineInfo = new CartLineInfo();
			lineInfo.setQuantity(0);
			lineInfo.setProductInfo(productInfo);
			this.cartLines.add(lineInfo);
		}
		int newQuantiy = lineInfo.getQuantity() + quantity;
		if(newQuantiy <= 0 ) {
			this.cartLines.remove(lineInfo);
		}else {
			lineInfo.setQuantity(newQuantiy);
		}
	}
	
	
	public void validate() {
		
	}
	
	
	// update gia ban cua san pham
	public void updateProduct(String code,int quantity) {
		CartLineInfo cartLineInfo = this.findLineByCode(code);
		
		if(cartLineInfo != null) {
			if(quantity <=0) {
				this.cartLines.remove(cartLineInfo);
			}else {
				cartLineInfo.setQuantity(quantity);
			}
		}
	}
	
	public void removeProduct(ProductInfo productInfo) {
		CartLineInfo cartLineInfo = this.findLineByCode(productInfo.getCode());
		if(cartLineInfo != null) {
			this.cartLines.remove(cartLineInfo);
		}
	}
	
	public boolean isEmpty() {
		return this.cartLines.isEmpty();
	}
	

	public boolean isValidCustomer() {
		return this.customerInfo != null && this.customerInfo.isValid();
	}
	
	public int getQuantityTotal() {
		int quantity = 0;
		for (CartLineInfo cartLineInfo : cartLines) {
			quantity = quantity +cartLineInfo.getQuantity();
		}
		return quantity;
	}
	
	public double getAmountTotal() {
		double total =0;
		for (CartLineInfo cartLineInfo : cartLines) {
			total = total + cartLineInfo.getAmount();
		}
		return total;
	}
	
	public void updateQuantity(CartInfo cartForm) {
		if(cartForm != null) {
			for (CartLineInfo cartLineInfo : cartLines) {
				this.updateProduct(cartLineInfo.getProductInfo().getCode(), cartLineInfo.getQuantity());
			}
		}
	}
	
}
