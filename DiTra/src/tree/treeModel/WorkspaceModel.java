/***********************************************************************
 * Module:  WorkspaceModel.java
 * Author:  PC
 * Purpose: Defines the Class WorkspaceModel
 ***********************************************************************/

package tree.treeModel;

import java.util.*;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import tree.treeView.InformacioniResursView;
import tree.treeView.WorkspaceView;



/** @pdOid 62df8281-4269-424a-84f4-966189ee727c */
public class WorkspaceModel extends DefaultTreeModel {
	
	private NodeFactory nodeFactory;
	private static WorkspaceView workspace = new WorkspaceView();
	private ArrayList<InformacioniResurs> infResursi= new ArrayList<InformacioniResurs>();
	
	public WorkspaceModel() {
		super(workspace);
		nodeFactory = new NodeFactory();
	}

	
	public void addInfRe(InformacioniResurs infRe)
	{
		infResursi.add(infRe);                                           //dodaje u listu modela infResursa
		((WorkspaceView)getRoot()).add(infRe.getTreeView());   //dodaje infresurs u stablo
	}
	
	
	public ArrayList<InformacioniResurs> getInfResursi() {
		return infResursi;
	}


	public static WorkspaceView getWorkspace() {
		return workspace;
	}
   

}