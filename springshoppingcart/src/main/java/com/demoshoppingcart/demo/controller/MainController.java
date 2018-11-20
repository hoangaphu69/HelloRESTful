package com.demoshoppingcart.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demoshoppingcart.demo.dao.OrderDAO;
import com.demoshoppingcart.demo.dao.ProductDAO;
import com.demoshoppingcart.demo.entity.Product;
import com.demoshoppingcart.demo.form.CustomerForm;
import com.demoshoppingcart.demo.model.CartInfo;
import com.demoshoppingcart.demo.model.CustomerInfo;
import com.demoshoppingcart.demo.model.ProductInfo;
import com.demoshoppingcart.demo.pagination.PaginationResult;
import com.demoshoppingcart.demo.utils.Utils;
import com.demoshoppingcart.demo.validator.CustomerFormValidator;

@Transactional
@Controller
public class MainController {

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CustomerFormValidator customerFormValidator;

	@InitBinder
	public void myInitBinder( WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if(target == null) {
			return;
		}
		//		see target

		System.out.println("target "+target);
		//		update sl tren gio hang 
		//		@ModelAttribute("cartFrom") @Validated CartInfo cartFrom

		if(target.getClass() == CartInfo.class) {

		}

		//		 truong hop save thong tin khach hang
		//		@ModelAttribute @Validated CustomerInfo customerInfo
		else if(target.getClass() == CustomerForm.class) {
			dataBinder.setValidator(customerFormValidator);
		}	
	}

	//	loi dang nhap nham trang 
	@RequestMapping(value = "/403")
	public String accessDenied() {
		return "/403";
	}

	//	home page
	@RequestMapping(value= "/")
	public String home() {
		return "index";
	}

	//	xu ly danh sach san pham

	@RequestMapping(value = "/productList")
	public String listProductHandler(Model model,
			@RequestParam(value = "name",defaultValue = "") String likeName,
			@RequestParam(value = "page",defaultValue = "1") int page) {

		final int maxResult = 5;
		final int maxNavigationPage = 10;
		
		PaginationResult<ProductInfo> result = productDAO.queryProducts(page, maxResult, maxNavigationPage,likeName);
		
		model.addAttribute("paginationProducts",result);
		
		// sem laii
		return "productList";
		
	}

//	xu ly trang mua danh sach product
	@RequestMapping(value = "/buyProduct")
	public String listProductHandler(HttpServletRequest request,Model model,
			@RequestParam(value ="code",defaultValue = "")String code) {

		Product product = null;
		if(code != null && code.length() > 0) {
			product = productDAO.findProduct(code);
			
		}
		if(product != null) {
			CartInfo cartInfo = Utils.getCartInSession(request);
			ProductInfo productInfo = new ProductInfo(product);
			cartInfo.addProduct(productInfo, 1);
			
		}
		
		return "redirect:/shoppingCart";
	}
	
//	xu ly xoa product 
	@RequestMapping(value = "/shoppingCartRemoveProduct")
	public String removeProductHandler(HttpServletRequest request,Model model,
			@RequestParam(value = "code",defaultValue = "")String code) {
		
		Product product = null;
		if(code != null && code.length()>0) {
			product = productDAO.findProduct(code);
		}
		if(product != null) {
			CartInfo cartInfo = Utils.getCartInSession(request);
			
			ProductInfo productInfo = new ProductInfo(product);
			
			cartInfo.removeProduct(productInfo);
		}
		
		
		return "redirect:/shoppingCart";
	}
	
//	cap nhap so luong cac san pham da mua
	@RequestMapping(value= {"/shoppingCart"},method = RequestMethod.POST)
	public String shoppingCartUpdateQuantity(HttpServletRequest request,Model model
			,@ModelAttribute("cartForm") CartInfo cartForm) {
		
		CartInfo cartInfo = Utils.getCartInSession(request);
		cartInfo.updateQuantity(cartForm);
		return "redirect:/shoppingCart";
	}
	
	
//	hien thi gio hang
	@RequestMapping(value = "/shoppingCart",method = RequestMethod.GET)
	public String shoppingCartHandler(HttpServletRequest request,Model model) {
		
		CartInfo cartInfo = Utils.getCartInSession(request);
		
		model.addAttribute("cartForm",cartInfo);
		return "shoppingCart";
	}
	
	
//	nhap thong tin khach hang
	@RequestMapping(value = "/shoppingCartCustomer",method = RequestMethod.GET)
	public String shoppingCartCustomerForm(HttpServletRequest request,Model model) {
		
		CartInfo cartInfo = Utils.getCartInSession(request);
		if(cartInfo.isEmpty()) {
			return "redirect:/shoppingCart";
		}
		
		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		
		CustomerForm customerForm = new CustomerForm(customerInfo);
		
		model.addAttribute("customerForm",customerForm);
		
		return "shoppingCartCustomer";
	}
	
//	post save thong tin khach hang
	@RequestMapping(value ="/shoppingCartCustomer",method  = RequestMethod.POST)
	public String shoppingCartCustomerSave(HttpServletRequest request,Model model
			,@ModelAttribute(value = "customerForm") @Validated CustomerForm customerForm
			,BindingResult result
			,final RedirectAttributes redirectAttributes) {
		
		
//		neu result sai thi quay lai trang nhap thong tin khach hang
		if(result.hasErrors()) {
			
			customerForm.setValid(false);
//			forward toi trang nhap 
			return "shoppingCartCustomer";
		}
		customerForm.setValid(true);
		CartInfo cartInfo = Utils.getCartInSession(request);
		
		CustomerInfo customerInfo = new CustomerInfo(customerForm);
		cartInfo.setCustomerInfo(customerInfo);
		
		return "redirect:/shoppingCartComfirmation";	
	}
	
//	GET xem lai thong tin
	@RequestMapping(value = "/shppingCartConfirmation",method = RequestMethod.GET)
	public String shoppingCartConfirmationReview(HttpServletRequest request,Model model) {
		
		CartInfo cartInfo = Utils.getCartInSession(request);
		if(cartInfo == null || cartInfo.isEmpty()) {
			return "redirect:/shoppingCart";
		}
		else if(!cartInfo.isValidCustomer()) {
			return "redirect:/shoppingCartCustomer";
		}
		
		model.addAttribute("myCart", cartInfo);
		return "shoppingCartConfirmation";
	}
//	POST gui don hang
	
	@RequestMapping(value = "/shoppingCartConfirmation",method = RequestMethod.POST)
	public String shoppingCartConfirmationSave(HttpServletRequest request,Model model) {
		CartInfo cartInfo = Utils.getCartInSession(request);
		if(cartInfo.isEmpty()) {
			return "redirect:/shoppingCart";
		}
		else if(cartInfo.isValidCustomer()) {
			return "redirect:/shoppingCartCustomer";
		}
		try {
			orderDAO.saveOrder(cartInfo);	
		} catch (Exception e) {
			// TODO: handle exception
			return "shoppingCartConfirmation";
		}
		Utils.storeLastOrderedCartInSesion(request, cartInfo);
		
		return "redirect:/shoppingCartFinalize";
	}
	
	@RequestMapping(value = "/shoppingCartFinalize",method = RequestMethod.GET)
	public String shoppingCartFinalize(HttpServletRequest request,Model model) {
		CartInfo lastOrderedCart = Utils.getLastOrderedCartInSesion(request);
		
		if(lastOrderedCart == null) {
			return "redirect:/shoppingCart";
		}
		model.addAttribute("lastOrderedCart", lastOrderedCart);
		return "shoppingCartFinalize";
	}	
	
	
//	GET image
	@RequestMapping(value = "/productImage",method = RequestMethod.GET)
	public void productImage(HttpServletRequest request,HttpServletResponse response
			,@RequestParam(value = "code")String code) throws IOException {
		Product product = null;
		if(code != null) {
			product = productDAO.findProduct(code);
		}
		if(product != null && product.getImage() != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(product.getImage());
		}
		
		response.getOutputStream().close();
	}
}













