/***********************************************************************
 * Module:  WorkSpaceTree.java
 * Author:  PC
 * Purpose: Defines the Class WorkSpaceTree
 ***********************************************************************/

package tree.treeView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import gui.MainFrame;
import tree.treeModel.InformacioniResurs;
import tree.treeModel.WorkspaceModel;


/** @pdOid 3eba6cf4-b99c-4bd5-bcb7-071b5758aedd */
public class WorkspaceTree extends JTree implements TreeSelectionListener {

   public WorkspaceTree() {
		
		addTreeSelectionListener(this);
		setCellEditor(new WorkspaceTreeEditor(this, new WorkspaceTreeCellRenderer()));
		setCellRenderer(new WorkspaceTreeCellRenderer());
		setEditable(true);
		addMouseListener(ml);
	}
	
	
	public void addInfRe(InformacioniResurs infRe)
	{
		((WorkspaceModel)getModel()).addInfRe(infRe);;
		SwingUtilities.updateComponentTreeUI(this);
		
	}
	
	
	
	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		//da se zavrsi kad bude trebalo
		
	}
	
	
	
	
	MouseListener ml = new MouseAdapter() {
	    public void mousePressed(MouseEvent e) {
	        
	    	int selRow = getRowForLocation(e.getX(), e.getY());
	    	TreePath selPath = getPathForLocation(e.getX(), e.getY());
	    	
	    	
	    	if(selRow != -1) {
	            if(e.getClickCount() == 2) {
	                
	            	MutableTreeNode node = (MutableTreeNode) selPath.getLastPathComponent();
	            	if(node instanceof EntitetView)
	            	{
	            		EntitetView entitet = (EntitetView) node;
	           
	            		
	            		String title = ((InformacioniResursView)entitet.getParent()).getInformacioniResursModel().getName()+"-"+entitet.getEntitetModel().getName();   
	            		
	            		//TablePanel panel = new TablePanel(entitet.getEntitetModel());
	            		
	            		//MainFrame.getInstance().getDesktop().add(title,panel);
	            		
	            		//MainFrame.getInstance().getDesktop().setSelectedComponent(panel);
	            		
	            		
	            	}
	            	
	     
	            }
	            
	        }
	        
	            
	    }
	};

}