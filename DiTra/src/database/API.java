package database;

import appFramework.Repository;

public class API extends AbstractAPI {
	
	

	public API(Repository repository) {
		super(repository);
		
	}

	@Override
	public void add() {
		repository.add();
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	

	
	
}
