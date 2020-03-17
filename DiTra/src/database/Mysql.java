package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import appFramework.Repository;
import gui.MainFrame;
import tree.treeModel.Atribut;
import tree.treeModel.Entitet;

public class Mysql implements Repository {

	@Override
	public void add(Entitet entitet, String[] redKojiSeDodaje) throws SQLException {
		
		String tableName = entitet.getName();
		String statement = "INSERT INTO `"+tableName+"` (";
		for (int i=0; i<entitet.getAtribut().size(); i++) 
		{
			if (i>0) statement+=",";
			statement+="`" + ((List<Atribut>)entitet.getAtribut()).get(i) + "`";
		}
		statement+=") VALUES (";
		for (int i=0; i<entitet.getAtribut().size(); i++) 
		{
			if (i>0) statement+=",";
			statement+="?";
		}
		statement+=")";
		//System.out.println(statement);
		PreparedStatement preparedStatement = MainFrame.getInstance().getModel().getConfig().getConnection().prepareStatement(statement);
		System.out.println(preparedStatement);
		for(int i=0; i<entitet.getAtribut().size();i++) 
		{
			preparedStatement.setString(i+1, redKojiSeDodaje[i]);
		}
		System.out.println(preparedStatement);
		preparedStatement.executeUpdate();
	}

	@Override
	public void remove(Entitet entitet, String[] redKojiSeDodaje) throws SQLException {
		// TODO Auto-generated method stub
		String tableName = entitet.getName();
		String statement = "DELETE FROM `"+tableName+"`";
		
		int k = 0;
		statement+= " WHERE ";
		statement+= "`" + entitet.getAtribut().get(k).getName() + "`";
		statement+= "=";
		statement+=  "'" + redKojiSeDodaje[k] + "'";
		System.out.println(statement);
		
		PreparedStatement preparedStatement = MainFrame.getInstance().getModel().getConfig().getConnection().prepareStatement(statement);
		
		preparedStatement.executeUpdate();
		
	}

	@Override
	public void update(Entitet entitet, String[] redKojiSeDodaje, String staraVrednost) throws SQLException {
		String tableName = entitet.getName();
		String statement = "UPDATE `"+tableName;
		
		statement+= "` SET ";
		for (int i=0; i<entitet.getAtribut().size(); i++) 
		{
			if (i>0) statement+=",";
			statement+= "`" + entitet.getAtribut().get(i).getName() + "` ";
			statement+= "=";
			statement+=  " '" + redKojiSeDodaje[i] + "'";
		}
		int k = entitet.getAtribut().size() - 1;
		statement+= " WHERE ";
		statement+= "`" + entitet.getAtribut().get(k).getName() + "` ";
		statement+= "=";
		statement+=  " '" + staraVrednost + "'";
		System.out.println(statement);
		
		PreparedStatement preparedStatement = MainFrame.getInstance().getModel().getConfig().getConnection().prepareStatement(statement);
		
		preparedStatement.executeUpdate();
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

}
