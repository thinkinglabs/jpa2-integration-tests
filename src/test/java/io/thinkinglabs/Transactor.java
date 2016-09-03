package io.thinkinglabs;

public interface Transactor {

	void perform(UnitOfWork unitOfWork) throws Exception;
	
	Object performQuery(QueryUnitOfWork queryUnitOfWork) throws Exception;

}