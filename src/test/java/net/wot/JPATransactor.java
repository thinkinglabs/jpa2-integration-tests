package net.wot;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

public class JPATransactor implements Transactor {
	private final EntityManager entityManager;

	public JPATransactor(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	public void perform(UnitOfWork unitOfWork) throws Exception {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			unitOfWork.work();
			transaction.commit();
		} catch (PersistenceException e) {
			throw e;
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
	}

	@Override
	public Object performQuery(QueryUnitOfWork queryUnitOfWork)
			throws Exception {
//		EntityTransaction transaction = entityManager.getTransaction();
		Object result = null;
		try {
//			transaction.begin();
			result = queryUnitOfWork.query();
//			transaction.commit();
		} catch (PersistenceException e) {
			throw e;
		} catch (Exception e) {
//			transaction.rollback();
			throw e;
		}
		return result;
	}
}
