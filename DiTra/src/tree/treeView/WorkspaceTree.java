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
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import database.File;
import database.FileView;
import gui.MainFrame;

import tree.treeModel.InformacioniResurs;
import tree.treeModel.WorkspaceModel;



/** @pdOid 3eba6cf4-b99c-4bd5-bcb7-071b5758aedd */
public class WorkspaceTree extends JTree implements TreeSelectionListener,MouseListener {

   public WorkspaceTree() {
		
		addTreeSelectionListener(this);
		setCellEditor(new WorkspaceTreeEditor(this, new WorkspaceTreeCellRenderer()));
		setCellRenderer(new WorkspaceTreeCellRenderer());
		setEditable(true);
		addMouseListener(this);
	}
	
	
	public void addInfRe(InformacioniResurs infRe)
	{
		((WorkspaceModel)getModel()).addInfRe(infRe);;
		SwingUtilities.updateComponentTreeUI(this);
		
	}
	
	
	/*
	 * Prilikom duplog klika na stablo koje predstavlja strukturu otvorene baze podataka,
	 * proverava se da li je korisnik kliknuo na čvor koji predstavlja tabelu baze podataka,
	 * ukoliko jeste pravi se nova instanca klase UIDBFile kojoj se prosleđuju ime
	 * tabele koju je korisnik odabrao. Klasa UIDBFile takođe nasleđuje klasu
	 * UIAbstractFile i u sebi sadrži imlementaciju metoda za učitavanje podataka,
	 * dodavanje, izmenu, brisanje, pretragu i sortiranje
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getButton()==MouseEvent.BUTTON1 && e.getClickCount()==2){
			DefaultMutableTreeNode dbNode=(DefaultMutableTreeNode) this.getLastSelectedPathComponent();
			if (dbNode instanceof EntitetView){
				File uidbfile=new File(((EntitetView)(dbNode)).getEntitetModel().getName());
				FileView fileView=new FileView(uidbfile);
				MainFrame.getInstance().setFileView(fileView);
			}
		}
	}
	
	
	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		//da se zavrsi kad bude trebalo
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}