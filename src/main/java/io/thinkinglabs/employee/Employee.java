package io.thinkinglabs.employee;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Employee
 *
 */
@Entity
@Access(AccessType.FIELD)
@SequenceGenerator(name = "seq_employee", sequenceName = "SEQ_EMPLOYEE")
public class Employee implements Serializable {

	private static final long serialVersionUID = 6189956720449003645L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_employee")
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
