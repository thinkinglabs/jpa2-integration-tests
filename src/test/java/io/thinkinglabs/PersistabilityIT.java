package io.thinkinglabs;

import io.thinkinglabs.company.CompanyBuilder;
import io.thinkinglabs.employee.EmployeeBuilder;
import org.apache.commons.beanutils.PropertyUtils;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Rule;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.Arrays;
import java.util.List;

import static io.thinkinglabs.company.CompanyBuilder.aCompany;
import static io.thinkinglabs.employee.EmployeeBuilder.johnDoe;
import static org.junit.Assert.assertThat;

public class PersistabilityIT {

    final private EntityManager entityManager = Persistence.createEntityManagerFactory(TestConstants.PERSISTENCE_UNIT_NAME).createEntityManager();
    final private Transactor transactor = new JPATransactor(entityManager);

    @Rule
    final public DatabaseMigrationRule databaseMigration = new DatabaseMigrationRule(entityManager, transactor);

    final List<? extends Builder<?>> persistentObjectBuilders =
            Arrays.asList(
                    aCompany().havingEmployees(persisted(johnDoe()))
            );


    private <T> Builder<T> persisted(final Builder<T> builder) {
        return new Builder<T>() {

            @Override
            public T build() {
                T entity = builder.build();
                entityManager.persist(entity);
                return entity;
            }

        };
    }

    @Test
    public void roundTripPersistentObjects() throws Exception {
        for (Builder<?> builder : persistentObjectBuilders) {
            assertCanBePersisted(builder);
        }
    }

    private void assertCanBePersisted(Builder<?> builder) throws Exception {
        try {
            assertReloadsWithSameStateAs(persistedObjectFromBuilder(builder));
        } catch (PersistenceException e) {
            throw new PersistenceException("could not round-trip " + typeNameFor(builder), e);
        }
    }

    private Object persistedObjectFromBuilder(final Builder<?> builder) throws Exception {
        return transactor.performQuery(new QueryUnitOfWork() {
            public Object query() throws Exception {
                Object original = builder.build();
                entityManager.persist(original);
                return original;
            }
        });
    }


    private void assertReloadsWithSameStateAs(final Object original) throws Exception {
        transactor.perform(new UnitOfWork() {

            @Override
            public void work() throws Exception {
                //TODO look at ShazamCrest for deep bean property matching
                assertThat(entityManager.find(original.getClass(), idOf(original)), SamePropertyValuesAs.samePropertyValuesAs(original));
            }
        });
    }

    protected Object idOf(Object original) throws Exception {
        return PropertyUtils.getProperty(original, "id");
    }

    private String typeNameFor(Builder<?> builder) {
        return builder.getClass().getSimpleName().replace("Builder", "");
    }
}
