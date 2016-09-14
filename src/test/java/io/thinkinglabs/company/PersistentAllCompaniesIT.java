package io.thinkinglabs.company;

import static io.thinkinglabs.company.CompanyBuilder.amazon;
import static io.thinkinglabs.company.CompanyBuilder.google;
import static io.thinkinglabs.company.CompanyBuilder.lehmanBrothers;
import static org.hamcrest.Matchers.contains;
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
                google(),
                amazon(),
                lehmanBrothers()
        );

        assertBankruptedCompanies(contains(companyNamed("Lehman Brothers")));
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
