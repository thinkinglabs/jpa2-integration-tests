package io.thinkinglabs;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import javax.persistence.EntityManager;
import java.sql.Connection;

public class DatabaseMigrationRule implements TestRule {

    private final EntityManager entityManager;
    private final Transactor transactor;

    public DatabaseMigrationRule(final EntityManager entityManager, final Transactor transactor) {
        this.entityManager = entityManager;
        this.transactor = transactor;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                transactor.perform(new UnitOfWork() {
                    @Override
                    public void work() throws Exception {
                        Connection connection = entityManager.unwrap(Connection.class);
                        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

                        Liquibase liquibase = new liquibase.Liquibase("io/thinkinglabs/db-changelog.yaml", new ClassLoaderResourceAccessor(), database);

                        liquibase.update(new Contexts(), new LabelExpression());
                    }
                });
                base.evaluate();
            }
        };
    }
}
