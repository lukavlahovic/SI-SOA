/***********************************************************************
 * Module:  Node.java
 * Author:  PC
 * Purpose: Defines the Class Node
 ***********************************************************************/

package tree.treeModel;

import java.util.*;

import javax.swing.tree.DefaultMutableTreeNode;


/** @pdOid 8e9fe774-61eb-487d-9cbd-222c3f0d5a1b */
public abstract class Node {

	private String name;
	private DefaultMutableTreeNode treeView;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public DefaultMutableTreeNode  getTreeView() {
		return treeView;
	}

	public void setTreeView(DefaultMutableTreeNode  treeView) {
		this.treeView  = treeView;
	}

	@Override
	public String toString() {
		return name;
	}	
	
}