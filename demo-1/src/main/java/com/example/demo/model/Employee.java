package com.example.demo.model;

import javax.persistence.Entity;

//@Entity
public class Employee {
	
	private String employeeNo;
	private String employeeName;
	private String position;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String employeeNo, String employeeName, String position) {
		super();
		this.employeeNo = employeeNo;
		this.employeeName = employeeName;
		this.position = position;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
	

}
