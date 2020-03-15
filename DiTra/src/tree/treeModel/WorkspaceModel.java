/***********************************************************************
 * Module:  WorkspaceModel.java
 * Author:  PC
 * Purpose: Defines the Class WorkspaceModel
 ***********************************************************************/

package tree.treeModel;

import java.util.*;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

/** @pdOid 62df8281-4269-424a-84f4-966189ee727c */
public class WorkspaceModel extends DefaultTreeModel {
	
   public WorkspaceModel(TreeNode root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

   public NodeFactory nodeFactory;

}