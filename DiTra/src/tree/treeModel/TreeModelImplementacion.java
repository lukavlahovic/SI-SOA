/***********************************************************************
 * Module:  TreeModelImplementacion.java
 * Author:  PC
 * Purpose: Defines the Class TreeModelImplementacion
 ***********************************************************************/

package tree.treeModel;

import tree.treeModel.WorkspaceModel;
import tree.treeView.WorkspaceTree;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import appFramework.TreeModel;
import database.config.ConfigImplementation;

public class TreeModelImplementacion extends java.util.Observable implements TreeModel {
   
   public WorkspaceModel workspaceModel;
   public WorkspaceTree workspaceTree;
   private ConfigImplementation config;
   
   
   public TreeModelImplementacion(ConfigImplementation config) {
	   createTreeModel();
	   createTreeView();
	   loadMetaSema();//test
	
	   this.config = config;
   }
   
   
   public void loadMetaSema() { //cita meta semu i pravi stablo
	   try {
		   Node ir = workspaceModel.getNodeFactory().makeNode("informacioni resurs");
		   workspaceModel.addInfRe(ir);
		   this.addObserver((Observer) ir);
		   DatabaseMetaData dbMetaData = config.getConnection().getMetaData();
		   String[] dbTypes={"TABLE"};
		   ResultSet rsTables=dbMetaData.getTables(null, null, null, dbTypes);
		   while (rsTables.next()){
			    String tableName=rsTables.getString("TABLE_NAME");
			    Node et = workspaceModel.getNodeFactory().makeNode("entitet");
			    this.addObserver((Observer) et);
			    ((InformacioniResurs)ir).addEntitet(et);
			    et.setName(tableName);
			    setChanged();
				notifyObservers((Entitet)et);
				ResultSet rsColumns= dbMetaData.getColumns(null, null,tableName,null);
				ResultSet pKeys= dbMetaData.getPrimaryKeys(null, null,tableName);
				ArrayList<String> keys = new ArrayList<>();
				while(pKeys.next()) {
					keys.add(pKeys.getString("COLUMN_NAME"));
				}
				while (rsColumns.next()){
					String columnName=rsColumns.getString("COLUMN_NAME"); 
					Node at = workspaceModel.getNodeFactory().makeNode("atribut");
					this.addObserver((Observer) at);
					String dataType = rsColumns.getString("TYPE_NAME");
					Node tip = workspaceModel.getNodeFactory().makeNode("opis");
					tip.setName("Type: " + dataType);
					String columnSize = rsColumns.getString("COLUMN_SIZE");
					Node velicina = workspaceModel.getNodeFactory().makeNode("opis");
					velicina.setName("Column size: " + columnSize);
					
					Node nulabilna = workspaceModel.getNodeFactory().makeNode("opis");
					int nullable = rsColumns.getInt("NULLABLE");
				    if (nullable == DatabaseMetaData.columnNullable) {
				    	nulabilna.setName("Nullable: " + "true");
				    }else {
				    	nulabilna.setName("Nullable: " + "false");
				    }
					((Entitet)et).addAtribut(at);
					at.setName(columnName);
					if(keys.contains(columnName)) {
						((Atribut)at).setKey(true);
						keys.remove(columnName);
					}
					setChanged();
					notifyObservers((Atribut)at);
					((Atribut)at).getOpisi().add((Opis)tip);
					setChanged();
					notifyObservers((Opis) tip);
					((Atribut)at).getOpisi().add((Opis)velicina);
					setChanged();
					notifyObservers((Opis) velicina);
					((Atribut)at).getOpisi().add((Opis)nulabilna);
					setChanged();
					notifyObservers((Opis) nulabilna);
				}
		   }
		
		System.out.println(dbMetaData.getTables(null, null, null, dbTypes));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }
   
   public void createTreeModel() {
	   
	   workspaceModel = new WorkspaceModel();
	   
   }

   @Override
   public void createTreeView() {
	
	   workspaceTree = new WorkspaceTree();
	   workspaceTree.setModel(workspaceModel);
   }


	public WorkspaceTree getWorkspaceTree() {
		return workspaceTree;
	}
   
   

}