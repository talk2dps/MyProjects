package com.debi.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.debi.ems.model.Employee;
import com.debi.ems.service.EmployeeService;

@RestController
@RequestMapping("/Employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@DeleteMapping("/{employeeId}/Projects/{projectId}")
	public ResponseEntity<Employee> deleteProjectFromEmployee(@PathVariable(value = "employeeId") Long employeeId,
			@PathVariable(value = "projectId") Long projectId) throws Exception {
		employeeService.deleteProjectFromEmployee(employeeId, projectId);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}
}
