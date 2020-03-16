/***********************************************************************
 * Module:  EntitetView.java
 * Author:  PC
 * Purpose: Defines the Class EntitetView
 ***********************************************************************/

package tree.treeView;

import java.util.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import tree.treeModel.Entitet;



/** @pdOid dbbd765a-1fe6-48a7-b37a-f3c0c023b9ad */
public class EntitetView extends DefaultMutableTreeNode {
	
	private Entitet entitet;


	public EntitetView(Entitet entitet) {
		
		this.entitet = entitet;
		
	}

	@Override
	public void add(MutableTreeNode arg0) {
		if(arg0 instanceof AtributView) 
		    super.add(arg0);
		else
			System.out.println("Neuspesno dodvanje atributa");
			
			
	}

	public Entitet getEntitetModel() {
		return entitet;
	}
	
	
	
	@Override
	public String toString() {
		return entitet.getName();
	}


	

	
}