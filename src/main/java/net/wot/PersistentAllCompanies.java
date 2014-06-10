package net.wot;

import javax.persistence.EntityManager;

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

}
