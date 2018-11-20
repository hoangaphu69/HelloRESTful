package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.model.Employee;

import net.bytebuddy.asm.Advice.Return;

@RestController
public class MainRESTController {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	//home :))
	@GetMapping(value = "/")
	@ResponseBody
	public String welcome() {
		return  "welcome to spring boot RESTtemplate example";
	}
	
	
	//get all employee
	// http://localhost:8080/SomeContextPath/employees
    // http://localhost:8080/SomeContextPath/employees.xml
    // http://localhost:8080/SomeContextPath/employees.json
	@RequestMapping(value="/employees",method = RequestMethod.GET
			,produces= {MediaType.APPLICATION_ATOM_XML_VALUE
					}
	)
	@ResponseBody
	public List<Employee> getAllEmployees(){
		List<Employee> list= employeeDAO.getAllEmployee();
		return list;
	}
	
	
	// URL:
    // http://localhost:8080/SomeContextPath/employee
    // http://localhost:8080/SomeContextPath/employee.xml
    // http://localhost:8080/SomeContextPath/employee.json
	@RequestMapping(value = "/employee",method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE
					,MediaType.APPLICATION_ATOM_XML_VALUE})
	@ResponseBody
	public Employee getEmployee(@PathVariable("employeeNo")String employeeNo ) {
		return employeeDAO.getEmployee(employeeNo);
	}
	
	
	@RequestMapping(value = "/employee", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE //
                    })
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee emp) {
 
        System.out.println("(Service Side) Creating employee: " + emp.getEmployeeNo());
 
        return employeeDAO.addEmployee(emp);
    }
 
    // URL:
    // http://localhost:8080/SomeContextPath/employee
    // http://localhost:8080/SomeContextPath/employee.xml
    // http://localhost:8080/SomeContextPath/employee.json
    @RequestMapping(value = "/employee", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee updateEmployee(@RequestBody Employee employee) {
 
        System.out.println("(Service Side) Editing employee: " + employee.getEmployeeNo());
 
        return employeeDAO.updateEmployee(employee);
    }
 
    // URL:
    // http://localhost:8080/SomeContextPath/employee/{empNo}
    @RequestMapping(value = "/employee/{employeeNo}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteEmployee(@PathVariable("employeeNo") String employeeNo) {
 
        System.out.println("(Service Side) Deleting employee: " + employeeNo);
 
        employeeDAO.deleteEmployee(employeeNo);
    }
	
}
