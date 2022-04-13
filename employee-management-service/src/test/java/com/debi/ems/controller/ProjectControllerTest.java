package com.debi.ems.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

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

@WebMvcTest
class ProjectControllerTest {

	@MockBean
	EmployeeService employeeService;
	
	@Autowired
	MockMvc mockMvc;

	@Test
	void findEmployeesByProjectIdTestWhenProjectExist() throws Exception {
		Employee employee1=new Employee();
		List<Employee> reponse=new ArrayList<>();
		reponse.add(employee1);
		String URI = "/Projects/1/Employees";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI);
		
		Mockito.when(employeeService.findEmployeesByProjectId(ArgumentMatchers.any())).thenReturn(reponse);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
	}
	
	@Test
	void findEmployeesByProjectIdTestWhenProjectDoesNotExist() throws Exception {
		String URI = "/Projects/1/Employees";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI);
		
		doThrow(NoDataFoundException.class).when(employeeService).findEmployeesByProjectId(ArgumentMatchers.any());

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
	}

}
