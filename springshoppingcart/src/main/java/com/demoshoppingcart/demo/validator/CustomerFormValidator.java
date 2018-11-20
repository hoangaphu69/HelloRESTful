package com.demoshoppingcart.demo.validator;

import org.apache.commons.validator.routines.EmailValidator;
//import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
//import org.apache.commons.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.demoshoppingcart.demo.form.CustomerForm;

@Component
public class CustomerFormValidator  implements Validator{

	private EmailValidator emailValidator = EmailValidator.getInstance();

	@Override
	public boolean supports(Class<?> clazz) {
		
		// TODO Auto-generated method stub
		return clazz == CustomerForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		CustomerForm customer = (CustomerForm) target;
		
//		kiem tra field cua form
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.CustomerForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","NotEmpty.CustomerForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.CustomerForm.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.CustomerForm.phone");
		
		if(!this.emailValidator.isValid(customer.getEmail())) {
			errors.reject("email","Pattern.CustomerForm.email");
		}
	
	}
}
