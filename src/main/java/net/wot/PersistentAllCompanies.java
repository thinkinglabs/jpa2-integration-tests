package net.wot;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class PersistentAllCompanies implements AllCompanies {

	private EntityManager entityManager;
	
	public PersistentAllCompanies(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public void addCompany(Company company) {
		entityManager.persist(company);
	}

	@Override
	public List<Company> findBankruptedCompanies() {
		TypedQuery<Company> query = entityManager.createQuery("select c from Company c where c.bankrupted = TRUE", Company.class);
		return query.getResultList();
	}

}
