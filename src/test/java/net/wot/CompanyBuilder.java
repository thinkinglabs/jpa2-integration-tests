package net.wot;

public class CompanyBuilder implements Builder<Company> {
	private String name = "a company";
	
	public static CompanyBuilder aCompany() {
		return new CompanyBuilder();
	}
	
	public CompanyBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public Company build() {
		return new Company(name);
	}
}
