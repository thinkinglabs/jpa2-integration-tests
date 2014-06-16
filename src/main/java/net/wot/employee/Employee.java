package net.wot.employee;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Employee
 *
 */
@Entity
@Access(AccessType.FIELD)
public class Employee implements Serializable {

	private static final long serialVersionUID = 6189956720449003645L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@SuppressWarnings("unused")
	private Employee() {
		super();
	}
	
	public Employee(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
}
