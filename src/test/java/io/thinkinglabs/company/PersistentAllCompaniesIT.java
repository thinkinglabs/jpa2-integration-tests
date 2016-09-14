package io.thinkinglabs.company;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import io.thinkinglabs.*;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static io.thinkinglabs.company.CompanyNamedMatcher.companyNamed;

public class PersistentAllCompaniesIT {

    final EntityManager entityManager = Persistence.createEntityManagerFactory(TestConstants.PERSISTENCE_UNIT_NAME).createEntityManager();
    final AllCompanies allCompanies = new PersistentAllCompanies(entityManager);
    final Transactor transactor = new JPATransactor(entityManager);

    @Rule
    final public DatabaseMigrationRule databaseMigration = new DatabaseMigrationRule(entityManager, transactor);

    @Test
    public void findBankruptedCompanies() throws Exception {
        addCompanies(
                CompanyBuilder.aCompany().withName("Foo Ltd. (bankrupt)").withBankrupted(true),
                CompanyBuilder.aCompany().withName("Bar Ltd. (bankrupt)").withBankrupted(true),
                CompanyBuilder.aCompany().withName("Winner Ltd.")
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
