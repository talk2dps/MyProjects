package com.debi.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.debi.ems.exception.NoDataFoundException;
import com.debi.ems.model.Employee;
import com.debi.ems.repository.EmployeeRepository;
import com.debi.ems.repository.ProjectRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ProjectRepository projectRepository;

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	public Employee deleteProjectFromEmployee(Long employeeId, Long projectId) throws Exception {
		Employee employee = employeeRepository.findById(employeeId)
		        .orElseThrow(() -> new NoDataFoundException("No Employee found with id = " + employeeId));
		    
		    employee.removeProject(projectId);
		    employee=employeeRepository.save(employee);
		    
		    return employee;
	}

	public List<Employee> findEmployeesByProjectId(Long projectId) throws Exception {
		if (!projectRepository.existsById(projectId)) {
			throw new NoDataFoundException("No Project Found with id: " + projectId);
		}
		List<Employee> employees = employeeRepository.findByProjectId(projectId);
		return employees;
	}
}
