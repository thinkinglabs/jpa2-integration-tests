package io.thinkinglabs.employee;

import io.thinkinglabs.Builder;

public class EmployeeBuilder implements Builder<Employee> {
	
	private String name = "an employee";
	
	public static EmployeeBuilder anEmployee() {
		return new EmployeeBuilder();
	}

	public static EmployeeBuilder johnDoe() {
	    return anEmployee().withName("John Doe");
    }
	
	public EmployeeBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public Employee build() {
		return new Employee(this.name);
	}
}
