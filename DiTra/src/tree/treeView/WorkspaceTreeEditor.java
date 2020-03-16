/***********************************************************************
 * Module:  WorkspaceTreeEditor.java
 * Author:  PC
 * Purpose: Defines the Class WorkspaceTreeEditor
 ***********************************************************************/

package tree.treeView;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;

import gui.MainFrame;



/** @pdOid 54faf1af-5f31-4dfe-bca3-fe9e2a3b309a */
public class WorkspaceTreeEditor extends DefaultTreeCellEditor {

	private Object stavka = null;
	private JTextField edit = null;
	private EntitetView entitet;
	
	public WorkspaceTreeEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4,
			int arg5) {
		// TODO Auto-generated method stub
		super.getTreeCellEditorComponent(arg0, arg1, arg2, arg3, arg4, arg5);
		stavka = arg1;
		
		edit = new JTextField(arg1.toString());
		edit.addActionListener(this);
		
		return edit;
		
		
		
	}

	@Override
	public boolean isCellEditable(EventObject arg0) {
		
		if (arg0 instanceof MouseEvent)			
			if (((MouseEvent)arg0).getClickCount()==3){
		
				return true;
			}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
	

}
