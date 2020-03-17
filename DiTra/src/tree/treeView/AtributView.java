/***********************************************************************
 * Module:  AtributView.java
 * Author:  PC
 * Purpose: Defines the Class AtributView
 ***********************************************************************/

package tree.treeView;

import java.util.*;

import javax.swing.tree.DefaultMutableTreeNode;

import tree.treeModel.Atribut;


/** @pdOid e913aa60-fadb-42d9-9a42-dba269a6d561 */
public class AtributView extends DefaultMutableTreeNode {
	
	private Atribut atribut;
	
	public AtributView(Atribut atribut) {
		
		this.atribut = atribut;
		
	}

	public Atribut getAtributModel() {
		return atribut;
	}
	
	@Override
	public String toString() {
		return atribut.getName();
	}
	
	
}