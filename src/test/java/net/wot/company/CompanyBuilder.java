package net.wot.company;

import net.wot.Builder;
import net.wot.company.Company;

public class CompanyBuilder implements Builder<Company> {
	private String name = "a company";
	private boolean bankrupted = false;
	
	public static CompanyBuilder aCompany() {
		return new CompanyBuilder();
	}
	
	public CompanyBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public CompanyBuilder withBankrupted(boolean bankrupted) {
		this.bankrupted = bankrupted;
		return this;
	}
	
	public Company build() {
		return new Company(name, bankrupted);
	}
}
