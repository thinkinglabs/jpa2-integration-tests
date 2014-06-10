package net.wot;

public interface Transactor {

	void perform(UnitOfWork unitOfWork) throws Exception;
	
	Object performQuery(QueryUnitOfWork queryUnitOfWork) throws Exception;

}