/***********************************************************************
 * Module:  WorkspaceView.java
 * Author:  PC
 * Purpose: Defines the Class WorkspaceView
 ***********************************************************************/

package tree.treeView;

import java.util.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;



/** @pdOid 3ba814ec-3512-4a97-8769-23e75a0d4019 */
public class WorkspaceView extends DefaultMutableTreeNode {
	
	public WorkspaceView() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public void add(MutableTreeNode arg0) {
		// TODO Auto-generated method stub
		if(arg0 instanceof InformacioniResursView) {
		  super.add(arg0);
		}
		else
			System.out.println("Neuspesno dodavanje informacionog resursa");
	}
	
	

	
	public String toString()
	{
		return "Workspace";
		
	}
}