package com.debi.ems.model;

import java.io.Serializable;
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

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2348502456018382041L;

	@Id
	@GeneratedValue
	@Column(name = "EMP_CD")
	Long id;

	@Column(name = "EMP_NAME")
	String employeeName;

	// @OneToMany(targetEntity = Project.class, cascade = CascadeType.ALL)
	// @JoinColumn(name = "EMP_CD",referencedColumnName = "EMP_CD")
	// Set<Project> projects;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
	          CascadeType.MERGE})
	@JoinTable(name = "EMP_PROJ_RELN", joinColumns = { @JoinColumn(name = "EMP_CD") }, inverseJoinColumns = {
			@JoinColumn(name = "PROJ_CD") })
	Set<Project> project;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
	          CascadeType.MERGE})
	@JoinTable(name = "EMP_DEPT_RELN", joinColumns = { @JoinColumn(name = "EMP_CD") }, inverseJoinColumns = {
			@JoinColumn(name = "DEPT_CD") })
	Set<Department> department;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Set<Project> getProject() {
		return project;
	}

	public void setProject(Set<Project> project) {
		this.project = project;
	}

	public Set<Department> getDepartment() {
		return department;
	}

	public void setDepartment(Set<Department> department) {
		this.department = department;
	}
	
	public void removeProject(Long projectId) {
	    Project project = this.project.stream().filter(t -> t.getId() == projectId).findFirst().orElse(null);
	    if (project != null) this.project.remove(project);
	    project.getEmployee().remove(this);
	  }
}
