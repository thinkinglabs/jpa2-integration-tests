package io.thinkinglabs.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.thinkinglabs.Builder;
import io.thinkinglabs.employee.Employee;

public class CompanyBuilder implements Builder<Company> {
	private String name = "a company";
	private boolean bankrupted = false;
	private List<Builder<Employee>> employees = new ArrayList<>();
	
	public static CompanyBuilder aCompany() {
		return new CompanyBuilder();
	}

	public static CompanyBuilder google() {
	    return aCompany().withName("Google");
    }

    public static CompanyBuilder amazon() {
        return aCompany().withName("Amazon");
    }

    public static CompanyBuilder lehmanBrothers() {
        return aCompany().withName("Lehman Brothers").withBankrupted(true);
    }
	
	public CompanyBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public CompanyBuilder withBankrupted(boolean bankrupted) {
		this.bankrupted = bankrupted;
		return this;
	}
	
	@SafeVarargs
	public final CompanyBuilder havingEmployees(final Builder<Employee>... employees) {
		this.employees.addAll(Arrays.asList(employees));
		return this;
	}
	
	public Company build() {
		Company company = new Company(name, bankrupted);
		for(Builder<Employee> employee: employees) {
			company.addEmployee(employee.build());
		}
		return company;
	}
}
