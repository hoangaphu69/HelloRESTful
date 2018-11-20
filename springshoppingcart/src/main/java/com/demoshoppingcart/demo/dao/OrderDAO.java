package com.demoshoppingcart.demo.dao;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demoshoppingcart.demo.entity.Order;
import com.demoshoppingcart.demo.entity.OrderDetail;
import com.demoshoppingcart.demo.entity.Product;
import com.demoshoppingcart.demo.model.CartInfo;
import com.demoshoppingcart.demo.model.CartLineInfo;
import com.demoshoppingcart.demo.model.CustomerInfo;
import com.demoshoppingcart.demo.model.OrderDetailInfo;
import com.demoshoppingcart.demo.model.OrderInfo;
import com.demoshoppingcart.demo.pagination.PaginationResult;

@Transactional
@Repository
public class OrderDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ProductDAO productDAO;

	// get max order number
	public int getMaxOrderNumber() {
		
		String sql = "select max(o.orderNumber) from " + Order.class.getName() + "o";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Integer> query = session.createQuery(sql,Integer.class);
		Integer  value = query.getSingleResult();
		if(value ==null)
			return 0;
		return value;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void saveOrder(CartInfo cartInfo) {
		Session session = this.sessionFactory.getCurrentSession();
		
		int orderNum = this.getMaxOrderNumber()+1;
		 Order order = new Order();
		 
		 order.setId(UUID.randomUUID().toString());
		 order.setOrderNum(orderNum);
		 order.setOrderDate(new Date());
		 order.setAmount(cartInfo.getAmountTotal());
		
		 CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		 order.setCustomerName(customerInfo.getName());
		 order.setCustomerEmail(customerInfo.getMail());
		 order.setCustomerPhone(customerInfo.getPhone());
		 order.setCustomerAddress(customerInfo.getAddress());
		 
		 
		 session.persist(order);
		 
		 List<CartLineInfo> lineInfos = cartInfo.getCartLines();
		 
		 for (CartLineInfo cartLineInfo : lineInfos) {
			 OrderDetail detail = new OrderDetail();
			 detail.setId(UUID.randomUUID().toString());
			 detail.setOrder(order);
			 detail.setAmount(cartLineInfo.getAmount());
			 detail.setPrice(cartLineInfo.getProductInfo().getPrice());
			 detail.setQuanity(cartLineInfo.getQuantity());
			
			String code = cartLineInfo.getProductInfo().getCode();
			Product product = this.productDAO.findProduct(code);
			detail.setProduct(product);
			
			session.persist(detail);
		}
		cartInfo.setOrderNum(orderNum);
		session.flush();
	}
	
	public PaginationResult<OrderInfo> listOrderInfo(int page,int maxResult,int maxNavigationPage){
		
		String sql = "Select new " + OrderInfo.class.getName()//
                + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
                + " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone) " + " from "
                + Order.class.getName() + " ord "//
                + " order by ord.orderNum desc";
		Session session = sessionFactory.getCurrentSession();
		Query<OrderInfo> query = session.createQuery(sql,OrderInfo.class);
		return new PaginationResult<OrderInfo>(query, page, maxResult, maxNavigationPage);
	}
	public Order findOrder(String orderId) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(Order.class, orderId);
	}
	
	/**
	 * get thong tin cua orderinfo
	 * thong tin bao gom
	 * orderId
	 * orderDate
	 * orderNum
	 * Amount
	 * customerName
	 * customerAddress
	 * customerEmail
	 * customerPhone
	 * */
	public OrderInfo getOrderInfo(String orderId) {
		Order order = this.findOrder(orderId);
		if(order == null) {
			return null;
		}
		return new OrderInfo(order.getId(),order.getOrderDate(),
				order.getOrderNum(),order.getAmount(),
				order.getCustomerName(),order.getCustomerAddress(),
				order.getCustomerEmail(),order.getCustomerPhone());
	}
	
	public List<OrderDetailInfo> listOrderDetailInfos(String orderId){
		
		String sql = "Select new " + OrderDetailInfo.class.getName() //
                + "(d.id, d.product.code, d.product.name , d.quanity,d.price,d.amount) "//
                + " from " + OrderDetail.class.getName() + " d "//
                + " where d.order.id = :orderId ";
		Session session = sessionFactory.getCurrentSession();
		Query<OrderDetailInfo> query = session.createQuery(sql,OrderDetailInfo.class);
		query.setParameter("orderId", orderId);
		return query.getResultList();
	}
	
}




