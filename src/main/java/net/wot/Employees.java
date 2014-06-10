package net.wot;

import java.util.ArrayList;

import org.eclipse.persistence.indirection.ValueHolderInterface;

public class Employees extends ArrayList<Employee> implements ValueHolderInterface {

	private static final long serialVersionUID = 8544436699089538258L;

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInstantiated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setValue(Object arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
