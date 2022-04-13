package com.debi.ems.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="PROJECT")
public class Project implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8846734667571922703L;
	
	@Id
	@GeneratedValue
	@Column(name="PROJ_CD")
	Long id;
	
	@Column(name="PROJ_NAME")
	String projectName;
	
	@ManyToMany(fetch = FetchType.LAZY,
		      cascade = {CascadeType.PERSIST,
		              CascadeType.MERGE},
		      mappedBy = "project")
	@JsonIgnore
	private Set<Employee> employee = new HashSet<Employee>();
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
	          CascadeType.MERGE})
	@JoinTable(name = "DEPT_PROJ_RELN", joinColumns = { @JoinColumn(name = "PROJ_CD") }, inverseJoinColumns = {
			@JoinColumn(name = "DEPT_CD") })
	@JsonIgnore
	private Set<Department> department = new HashSet<Department>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Set<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}

	public Set<Department> getDepartment() {
		return department;
	}

	public void setDepartment(Set<Department> department) {
		this.department = department;
	}
	
	
}
