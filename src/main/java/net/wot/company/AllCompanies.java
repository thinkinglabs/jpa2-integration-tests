package net.wot.company;

import java.util.List;

public interface AllCompanies {
	void addCompany(Company company);

	List<Company> findBankruptedCompanies();
}
