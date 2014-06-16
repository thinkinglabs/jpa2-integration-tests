package net.wot.employee;

import net.wot.Builder;

public class EmployeeBuilder implements Builder<Employee> {
	
	private String name = "an employee";
	
	public static EmployeeBuilder anEmployee() {
		return new EmployeeBuilder();
	}
	
	public EmployeeBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public Employee build() {
		return new Employee(this.name);
	}
}
