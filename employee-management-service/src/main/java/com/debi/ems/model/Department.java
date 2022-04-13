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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name ="DEPARTMENT")
@Data
public class Department implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3100665871867990614L;
	
	@Id
	@GeneratedValue
	@Column(name="DEPT_CD")
	Long departmentCode;
	
	@Column(name="DEPT_NAME")	
	String departmentName;
	
	@ManyToMany(fetch = FetchType.LAZY,
		      cascade = {CascadeType.PERSIST,
		              CascadeType.MERGE},
		      mappedBy = "department")
	@JsonIgnore
	private Set<Employee> employee = new HashSet<Employee>();
	
	@ManyToMany(fetch = FetchType.LAZY,
		      cascade = {CascadeType.PERSIST,
		              CascadeType.MERGE},
		      mappedBy = "department")
	@JsonIgnore
	Set<Project> project;
}
