package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

@Repository
public class EmployeeDAO {
	private static final Map<String, Employee> employeeMap= new HashMap<String,Employee>();
	
	static {
		initEmployee();
	}
	
	
	///
	public static void initEmployee() {
		Employee employee = new Employee("e01","hoang dinh phu","clerk");
		Employee employee2 = new Employee("e02","huynh dinh phu","selesman");
		Employee employee3 = new Employee("e03","huynh hoang phu","manager");
		
		employeeMap.put(employee.getEmployeeNo(), employee);
		employeeMap.put(employee2.getEmployeeNo(), employee2);
		employeeMap.put(employee3.getEmployeeNo(), employee3);
	}
	
	// get employee 
	public Employee getEmployee(String employeeNo) {
		return employeeMap.get(employeeNo);
	}
	
	// add employee
	public Employee addEmployee(Employee employee) {
		employeeMap.put(employee.getEmployeeNo(), employee);
		return employee;
	}
	
//	update employee
	public Employee updateEmployee(Employee employee) {
		employeeMap.put(employee.getEmployeeNo(), employee);
		return employee;
	}
	
	//delete employee
	public void deleteEmployee(String employeeNo) {
		employeeMap.remove(employeeNo);
	}
	
//	get all employee
	public List<Employee> getAllEmployee(){
		Collection<Employee> c = employeeMap.values();
		List<Employee> list = new ArrayList<Employee>();
		list.addAll(c);
		return list;
	}
	
}