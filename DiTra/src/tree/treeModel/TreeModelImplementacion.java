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
   
   
   public void loadMetaSema() {
	   /*Node ir = workspaceModel.getNodeFactory().makeNode("informacioni resurs");
	   Node et = workspaceModel.getNodeFactory().makeNode("entitet");
	   Node at = workspaceModel.getNodeFactory().makeNode("atribut");
	   workspaceModel.addInfRe(ir);
	   ((InformacioniResurs)ir).addEntitet(et);
	   this.addObserver((Observer) ir);
	   this.addObserver((Observer) et);
	   setChanged();
	   notifyObservers((Entitet)et);
	   ((Entitet)et).addAtribut(at);
	   setChanged();
	   notifyObservers((Atribut)at);
	   ir.setName("ir");
	   et.setName("et");
	   at.setName("at");*/
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
			    ((InformacioniResurs)ir).addEntitet(et);
			    et.setName(tableName);
			    setChanged();
				notifyObservers((Entitet)et);
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