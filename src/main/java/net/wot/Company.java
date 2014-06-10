package net.wot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Company
 *
 */
@Entity
@Access(AccessType.FIELD)
public class Company implements Serializable {

	private static final long serialVersionUID = 3537556775222721424L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@OneToMany(cascade=CascadeType.PERSIST, targetEntity=Employee.class)
	@JoinColumn(name="COMPANY_ID")
	private Employees employees;
	
	private Company() {
		super();
		this.employees = new Employees();
	}
	
	public Company(String name)
	{
		this();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}
   
	
}
