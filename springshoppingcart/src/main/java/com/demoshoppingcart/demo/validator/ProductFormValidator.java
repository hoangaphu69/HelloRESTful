package com.demoshoppingcart.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.demoshoppingcart.demo.dao.ProductDAO;
import com.demoshoppingcart.demo.entity.Product;
import com.demoshoppingcart.demo.form.ProductForm;

@Component
public class ProductFormValidator implements Validator {

	@Autowired
	private ProductDAO productDAO;
	
	
	
//	kiem tra class
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == ProductForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ProductForm productForm = (ProductForm) target;
	
//		kiem tra field cua productFrom
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty.productForm.code");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "notEmpty.productFrom.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productFrom.price");
		
		String code  = productForm.getCode();
		if(code != null && code.length()>0) {
			if(code.matches("\\s+")) {
				errors.rejectValue("code", "Pattern.productFrom.code");
			}
			else if(productForm.isNewProduct()) {
				Product product = productDAO.findProduct(code);
				if(product != null){
					errors.rejectValue("code", "Duplicate.productFrom.code");
				}
			}
		}
		
	}
	
}
