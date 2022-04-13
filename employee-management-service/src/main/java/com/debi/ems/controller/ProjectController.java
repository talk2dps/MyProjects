package com.debi.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.debi.ems.model.Employee;
import com.debi.ems.service.EmployeeService;

@RestController
@RequestMapping("/Projects")
public class ProjectController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping(value = "/{id}/Employees",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> findEmployeesByProjectId(@PathVariable(value = "id") Long id) throws Exception {
		List<Employee> response=employeeService.findEmployeesByProjectId(id);
		return new ResponseEntity<List<Employee>>(response,HttpStatus.OK);
	}

}
