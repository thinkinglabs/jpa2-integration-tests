package net.wot;

import static org.junit.Assert.*;
import static net.wot.CompanyNamedMatcher.companyNamed;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static net.wot.CompanyBuilder.aCompany;

public class PersistentAllCompaniesIT {

	final EntityManager entityManager = Persistence.createEntityManagerFactory("integration").createEntityManager();
	final AllCompanies allCompanies = new PersistentAllCompanies(entityManager);
	final Transactor transactor = new JPATransactor(entityManager);
	
	@Test
	public void findBankruptedCompanies() throws Exception {
		addCompanies(
			aCompany().withName("Foo Ltd. (bankrupt)").withBankrupted(true),
			aCompany().withName("Bar Ltd. (bankrupt)").withBankrupted(true),
			aCompany().withName("Winner Ltd.")
		);
		
		assertBankruptedCompanies(
				containsInAnyOrder(
					companyNamed("Foo Ltd. (bankrupt)"), 
					companyNamed("Bar Ltd. (bankrupt)")));
	}
	
	private void addCompanies(final CompanyBuilder... companies) throws Exception {
		transactor.perform(new UnitOfWork() {
			
			@Override
			public void work() throws Exception {
				for (CompanyBuilder company : companies) {
					allCompanies.addCompany(company.build());
				}
			}
		});
	}

	private void assertBankruptedCompanies(
			final Matcher<Iterable<? extends Company>> matcher) throws Exception {
		transactor.perform(new UnitOfWork() {
			
			@Override
			public void work() throws Exception {
				assertThat(allCompanies.findBankruptedCompanies(), matcher);
			}
		});		
	}

}
