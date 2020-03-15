/***********************************************************************
 * Module:  WorkSpaceTree.java
 * Author:  PC
 * Purpose: Defines the Class WorkSpaceTree
 ***********************************************************************/

package tree.treeView;

import java.util.*;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

/** @pdOid 3eba6cf4-b99c-4bd5-bcb7-071b5758aedd */
public class WorkspaceTree extends JTree implements TreeSelectionListener {
   /** @pdRoleInfo migr=no name=WorkspaceTreeCellRenderer assc=association1 mult=1..1 type=Composition */
   public WorkspaceTreeCellRenderer workspaceTreeCellRenderer;
   /** @pdRoleInfo migr=no name=WorkspaceTreeEditor assc=association2 mult=1..1 type=Composition */
   public WorkspaceTreeEditor workspaceTreeEditor;
@Override
public void valueChanged(TreeSelectionEvent e) {
	// TODO Auto-generated method stub
	
}

}