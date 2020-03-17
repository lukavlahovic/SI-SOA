package database;

import appFramework.Repository;
import tree.treeModel.Entitet;

public abstract class AbstractAPI {
	
	protected Repository repository;
	abstract public void add(Entitet entitet,String[] redKojiSeDodaje);
	abstract public void remove(Entitet entitet,String[] redKojiSeDodaje);
	abstract public void update(Entitet entitet,String[] redKojiSeDodaje);
	abstract public void save();
	
	public AbstractAPI(Repository repository) {
		this.repository = repository;
	}
	
	

}
