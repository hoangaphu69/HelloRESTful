package com.demoshoppingcart.demo.controller;

import java.util.List;

import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
//import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.WebDataBinder;

import com.demoshoppingcart.demo.model.OrderDetailInfo;
import com.demoshoppingcart.demo.dao.OrderDAO;
import com.demoshoppingcart.demo.dao.ProductDAO;
import com.demoshoppingcart.demo.entity.Product;
import com.demoshoppingcart.demo.form.ProductForm;
import com.demoshoppingcart.demo.model.OrderInfo;
import com.demoshoppingcart.demo.pagination.PaginationResult;
import com.demoshoppingcart.demo.validator.ProductFormValidator;

@Transactional
@Controller
public class AdminController {

	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ProductFormValidator productFormValidator;
	
	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target  = dataBinder.getTarget();
		if(target == null) {
			return ;
		}
		if(target.getClass() ==  ProductForm.class) {
			dataBinder.setValidator( productFormValidator);
		}
	}
	
//	hien thi trang login
	@RequestMapping(value = "/admin/login",method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/admin/accountInfo")
	public String accountInffo(Model model) {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication();

		System.out.println("password "+ userDetails.getPassword());
		System.out.println("userName "+ userDetails.getUsername());
		System.out.println("isEnabled "+ userDetails.isEnabled());
		
		model.addAttribute("userDetails",userDetails);
		return "accountInfo";
	}
	
	@RequestMapping(value = "/admin/orderList",method = RequestMethod.GET)
	public String  orderList(Model model
			,@RequestParam(value = "page",defaultValue = "1")String pageStr) {
		
		int page = 1;
		try {
		
			page = Integer.parseInt(pageStr);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		final int MAX_RESULT = 5;
		final int MAX_NAVIGATION_PAGE = 10;
		
		PaginationResult<OrderInfo> paginationResult =
				orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
		
		model.addAttribute("paginationResult",paginationResult);
		return "orderList";
	}
	
//	hien thi product
	@RequestMapping(value = "/admin/product",method = RequestMethod.GET)
	public String product(Model model,@RequestParam(value = "code",defaultValue = "")String code) {
		ProductForm productForm = null;
		if(code != null && code.length() > 0) {
			Product product = productDAO.findProduct(code);
			if(product != null) {
				productForm = new ProductForm(product.getCode(),product.getName(),product.getPrice());
			}
		}
		if(productForm == null) {
			productForm = new ProductForm();
			productForm.setNewProduct(true);
		}
		
		model.addAttribute("productForm",productForm);
		return "product";
	}
	@RequestMapping(value = { "/admin/product" }, method = RequestMethod.POST)
    public String productSave(Model model, //
            @ModelAttribute("productForm") @Validated ProductForm productForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
 
        if (result.hasErrors()) {
            return "product";
        }
        try {
            productDAO.save(productForm);
        } catch (Exception e) {
            Throwable rootCause = ExceptionUtils.unwrapInvocationTargetException(e);
            String message = rootCause.getMessage();
            model.addAttribute("errorMessage", message);
            // Show product form.
            return "product";
        }
 
        return "redirect:/productList";
    }
 
    @RequestMapping(value = { "/admin/order" }, method = RequestMethod.GET)
    public String orderView(Model model, @RequestParam("orderId") String orderId) {
        OrderInfo orderInfo = null;
        if (orderId != null) {
            orderInfo = this.orderDAO.getOrderInfo(orderId);
        }
        if (orderInfo == null) {
            return "redirect:/admin/orderList";
        }
        List<OrderDetailInfo> details = this.orderDAO.listOrderDetailInfos(orderId);
        orderInfo.setDetails(details);
 
        model.addAttribute("orderInfo", orderInfo);
 
        return "order";
    }
}










