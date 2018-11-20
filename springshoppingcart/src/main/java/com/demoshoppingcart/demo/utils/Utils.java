package com.demoshoppingcart.demo.utils;

import javax.servlet.http.HttpServletRequest;

import com.demoshoppingcart.demo.model.CartInfo;

public class Utils {
	
//	thong tin cac san pham duoc luu trong sesion
	public static CartInfo getCartInSession(HttpServletRequest request) {
		CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("myCart");
		
		if(cartInfo == null) {
			cartInfo = new CartInfo();
			request.getSession().setAttribute("myCart",cartInfo);
		}
		
		
		return cartInfo;
	}
	
//	xoa my cart
	public static void removeCartInSession(HttpServletRequest request) {
		request.getSession().removeAttribute("myCart");
	}
	
	public static void storeLastOrderedCartInSesion(HttpServletRequest request,CartInfo cartInfo) {
		request.getSession().setAttribute("lastOrderedCart", cartInfo);
	}
	
	public static CartInfo getLastOrderedCartInSesion(HttpServletRequest request) {
		return (CartInfo) request.getSession().getAttribute("lastOrderedCart");
	}
	
	

}
