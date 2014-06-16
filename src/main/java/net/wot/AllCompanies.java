package net.wot;

import java.util.List;

public interface AllCompanies {
	void addCompany(Company company);

	List<Company> findBankruptedCompanies();
}
