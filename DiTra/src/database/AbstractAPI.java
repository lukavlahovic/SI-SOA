package database;

import appFramework.Repository;

public abstract class AbstractAPI {
	
	protected Repository repository;
	abstract public void add();
	abstract public void remove();
	abstract public void update();
	abstract public void save();
	
	public AbstractAPI(Repository repository) {
		this.repository = repository;
	}
	
	

}
