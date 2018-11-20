package com.shoppingcart.demo.service;

import java.util.List;
import java.util.Optional;

import com.shoppingcart.demo.model.Employee;

public interface EmployeeService {
	Iterable<Employee> findAll();

	List<Employee> search(String q);
	
	Optional<Employee> findOne(int id);
	
	void save(Employee contact);
	
	void delete(int id);
}
