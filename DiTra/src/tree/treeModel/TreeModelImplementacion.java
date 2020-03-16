/***********************************************************************
 * Module:  TreeModelImplementacion.java
 * Author:  PC
 * Purpose: Defines the Class TreeModelImplementacion
 ***********************************************************************/

package tree.treeModel;

import tree.treeModel.WorkspaceModel;
import tree.treeView.WorkspaceTree;
import java.util.*;

import appFramework.TreeModel;

public class TreeModelImplementacion implements TreeModel {
   
   public WorkspaceModel workspaceModel;
   public WorkspaceTree workspaceTree;
   
   public TreeModelImplementacion() {
	   createTreeModel();
	   createTreeView();
	   loadMetaSema();//test
   }
   
   
   public void loadMetaSema() {
	   Node ir = workspaceModel.getNodeFactory().makeNode("informacioni resurs");
	   Node et = workspaceModel.getNodeFactory().makeNode("entitet");
	   Node at = workspaceModel.getNodeFactory().makeNode("atribut");
	   workspaceModel.addInfRe(ir);
	   ((InformacioniResurs)ir).addEntitet((Entitet) et);
	   ((InformacioniResurs)ir).getTreeView().add(((Entitet)et).getTreeView());
	   ((Entitet)et).addAtribut((Atribut)at);
	   ((Entitet)et).getTreeView().add(((Atribut)at).getTreeView());
	   ir.setName("ir");
	   et.setName("et");
	   at.setName("at");
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