/***********************************************************************
 * Module:  InformacioniResursView.java
 * Author:  PC
 * Purpose: Defines the Class InformacioniResursView
 ***********************************************************************/

package tree.treeView;

import java.util.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import tree.treeModel.InformacioniResurs;



/** @pdOid e01f205f-38e3-4b5c-8681-67eec24084c4 */
public class InformacioniResursView extends DefaultMutableTreeNode {
	
	private InformacioniResurs informacioniResurs;
	
	public InformacioniResursView(InformacioniResurs informacioniResurs) {
		
		this.informacioniResurs = informacioniResurs;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(MutableTreeNode arg0) {
		if(arg0 instanceof EntitetView)
		    super.add(arg0);
		else
			System.out.println("Neuspesno dodavanje paketa");
	}

	public InformacioniResurs getInformacioniResursModel() {
		return informacioniResurs;
	}
	
	
	@Override
	public String toString() {
		return informacioniResurs.getName();
	}
	
	
}