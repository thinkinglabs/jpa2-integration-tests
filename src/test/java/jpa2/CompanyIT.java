package jpa2;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import net.wot.Company;
import net.wot.Employee;

import org.junit.Before;
import org.junit.Test;

public class CompanyIT {

	private EntityManager em;
	private EntityTransaction tx;
	
	@Before
	public void setUp() throws Exception {
		em = Persistence.createEntityManagerFactory("integration").createEntityManager();
		tx = em.getTransaction();
	}

	@Test
	public void create() {
		Company company = new Company("a company", false);
		tx.begin();
		em.persist(company);
		tx.commit();
		Company actual = em.find(Company.class, company.getId());
		assertNotNull(actual);
	}
	
	@Test
	public void addEmployee() {
		Company company = new Company("a company", false);
		Employee employee = new Employee("toto");
		company.addEmployee(employee);
		tx.begin();
		em.persist(company);
		tx.commit();
		Company actualCompany = em.find(Company.class, company.getId());
		assertNotNull(actualCompany);
		Employee actualEmployee = em.find(Employee.class, employee.getId());
		assertNotNull(actualEmployee);
	}
}
