package database;

import java.sql.SQLException;

import appFramework.Repository;
import tree.treeModel.Entitet;

public class API extends AbstractAPI {
	
	

	public API(Repository repository) {
		super(repository);
		
	}

	@Override
	public void add(Entitet entitet, String[] redKojiSeDodaje) {
		try {
			repository.add(entitet, redKojiSeDodaje);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void remove(Entitet entitet,String[] redKojiSeDodaje) {
		// TODO Auto-generated method stub
		try {
			repository.remove(entitet, redKojiSeDodaje);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
