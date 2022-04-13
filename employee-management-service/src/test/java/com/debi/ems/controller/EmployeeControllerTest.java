package com.debi.ems.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.debi.ems.exception.NoDataFoundException;
import com.debi.ems.model.Employee;
import com.debi.ems.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
	
	@MockBean
	EmployeeService employeeService;
	
	@Autowired
	MockMvc mockMvc;

	@Test
	void deleteProjectFromEmployeeTestWhenEmployeeExist() throws Exception {
		Employee employee=new Employee();
		String URI = "/Employees/1/Projects/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI);
		
		Mockito.when(employeeService.deleteProjectFromEmployee(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(employee);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());
	}
	
	@Test
	void deleteProjectFromEmployeeTestWhenEmployeeDoesntExist() throws Exception {
		String URI = "/Employees/1/Projects/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI);
		
		doThrow(NoDataFoundException.class).when(employeeService).deleteProjectFromEmployee(ArgumentMatchers.any(),ArgumentMatchers.any());

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
	}
}
