package io.thinkinglabs.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import io.thinkinglabs.BaseEntity;
import io.thinkinglabs.employee.Employee;

/**
 * Entity implementation class for Entity: Company
 *
 */
@Entity
@Access(AccessType.FIELD)
@SequenceGenerator(name = "seq_company", sequenceName = "SEQ_COMPANY")
public class Company extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_company")
	private Long id;

	private String name;

	private boolean bankrupted;

	@OneToMany(cascade=CascadeType.PERSIST, targetEntity=Employee.class)
	@JoinColumn(name="COMPANY_ID")
	private List<Employee> employees;

	private Company() {
		super();
		this.employees = new ArrayList<>();
	}

	public Company(String name, boolean bankrupted)
	{
		this();
		this.name = name;
		this.bankrupted = bankrupted;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isBankrupted() {
		return bankrupted;
	}

	public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	@Override
	public String toString() {
		return "Company [" + name + "]";
	}

}
